package is.gudmundur1.unitofworkdemo;

/**
 * Created by gudmundur on 21.7.2016.
 */
public class Recipe extends DomainObject {

    private Long id;
    private final String name;

    public Recipe(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Recipe create(Long id, String name) {
        Recipe recipe = new Recipe(id, name);
        recipe.markNew();
        return recipe;
    }

    @Override
    public Object getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
