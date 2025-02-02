package me.yokeyword.fragmentation.queue;

import android.os.Handler;
import android.os.Looper;

import java.util.LinkedList;
import java.util.Queue;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportHelper;

/**
 * The queue of perform action.
 * <p>
 * Created by YoKey on 17/12/29.
 */
public class ActionQueue {

    private Queue<Action> mQueue = new LinkedList<>();
    private Handler mMainHandler;

    public ActionQueue(Handler mainHandler) {
        this.mMainHandler = mainHandler;
    }

    public void enqueue(final Action action) {
        if (isThrottleBACK(action)) return;

        if (action.action == Action.ACTION_LOAD && mQueue.isEmpty()
                && Thread.currentThread() == Looper.getMainLooper().getThread()) {
            action.run();
            return;
        }

        mMainHandler.post(() -> enqueueAction(action));
    }

    private void enqueueAction(Action action) {
        mQueue.add(action);
        if (mQueue.size() == 1) {
            handleAction();
        }
    }

    private void handleAction() {
        if (mQueue.isEmpty()) return;

        Action action = mQueue.peek();
        assert action != null;
        action.run();

        executeNextAction(action);
    }

    private void executeNextAction(Action action) {
        if (action.action == Action.ACTION_POP) {
            ISupportFragment top = SupportHelper.getBackStackTopFragment(action.fragmentManager);
            action.duration = top == null ? Action.DEFAULT_POP_TIME : top.getSupportDelegate().getExitAnimDuration();
        }

        mMainHandler.postDelayed(() -> {
            mQueue.poll();
            handleAction();
        }, action.duration);
    }

    private boolean isThrottleBACK(Action action) {
        if (action.action == Action.ACTION_BACK) {
            Action head = mQueue.peek();
            return head != null && head.action == Action.ACTION_POP;
        }
        return false;
    }
}
