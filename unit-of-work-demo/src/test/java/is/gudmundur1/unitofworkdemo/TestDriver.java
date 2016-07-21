package is.gudmundur1.unitofworkdemo;

/**
 * Created by gudmundur on 21.7.2016.
 */
public class TestDriver {
    public void createRecipe() {
        UnitOfWork.newCurrent();
        Recipe recipe = Recipe.create(1L, "Ham & cheese sandwitch");
        // todo: add ingredients to recipe

        UnitOfWork.getCurrent().commit();
    }
}
