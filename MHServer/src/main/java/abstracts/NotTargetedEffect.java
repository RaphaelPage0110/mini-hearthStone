package abstracts;

import inter.Target;

public abstract class NotTargetedEffect extends Effect {

    @Override
    public void effect(Target myTarget) {
    }

    public abstract void effect();
}
