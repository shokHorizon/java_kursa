package MVC;

import java.util.stream.Stream;

public interface IController {
    public IModel[] process(IModel[] models);
}
