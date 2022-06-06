package ChainOfResponsibility;

import Fish.Fish;

public abstract class BaseHandler {

    private BaseHandler nextHandler;

    public BaseHandler(BaseHandler baseHandler) {

        this.nextHandler = baseHandler;
    }

    public abstract Fish check();

    protected Fish checkNext () {
        if(nextHandler == null)
            return null;

        return nextHandler.check();
    }
}
