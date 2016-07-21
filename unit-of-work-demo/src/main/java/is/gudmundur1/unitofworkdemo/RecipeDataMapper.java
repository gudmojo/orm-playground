package is.gudmundur1.unitofworkdemo;

/**
 * Created by gudmundur on 21.7.2016.
 */
public class RecipeDataMapper extends AbstractDataMapper {

    @Override
    public void insert(DomainObject obj) {
        Recipe recipe = (Recipe) obj;
    }

    @Override
    public void update(DomainObject obj) {
        Recipe recipe = (Recipe) obj;

    }

    @Override
    public void delete(DomainObject obj) {
        Recipe recipe = (Recipe) obj;

    }
}
