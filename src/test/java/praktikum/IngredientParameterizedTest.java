package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientParameterizedTest {
    private final IngredientType type;
    private final String name;
    private final float price;
    private Ingredient ingredient;

    public IngredientParameterizedTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Тип ингредиента: {0}, Имя ингредиента: {1}, Цена: {2}")
    public static Object[][] getValueIngredient() {
        return new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100f},
                {IngredientType.SAUCE, "sour cream", 200f},
                {IngredientType.SAUCE, "chili sauce", 300f},
                {IngredientType.FILLING, "cutlet", 100f},
                {IngredientType.FILLING, "dinosaur", 200f},
                {IngredientType.FILLING, "sausage", 300f},
        };
    }

    @Before
    public void setUp() {
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void getPriceIngredient() {
        assertEquals("Неправильная цена ингредиента", price, ingredient.getPrice(), 0);
    }

    @Test
    public void getNameIngredient() {
        assertEquals("Неправильное название ингредиента", name, ingredient.getName());
    }

    @Test
    public void getIngredientType() {
        assertEquals("Неправильный тип ингредиента", type, ingredient.getType());
    }
}
