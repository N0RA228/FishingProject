package ChainOfResponsibility;

import Fish.Fish;

public class NegativeHandler extends BaseHandler{

    public NegativeHandler(BaseHandler baseHandler) {
        super(baseHandler);
    }

    @Override
    public Fish check() {
        return new Fish("Удочка неожиданно сломалась!", 0, 100);
    }
}
