package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    Bun bun;
    @Mock
    Ingredient sauce;
    @Mock
    Ingredient filling;
    private Burger burger;

    @Before
    public void openMocks() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void getValidBun() {
        burger.setBuns(bun);
        Bun actual = burger.bun;
        assertEquals("Неправильная булочка", bun, actual);
    }

    @Test
    public void getValidPrice() {
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(filling.getPrice()).thenReturn(50f);
        burger.setBuns(bun);
        burger.addIngredient(filling);
        assertEquals("Цена бургера неверная!", 250f, burger.getPrice(), 0);
    }

    @Test
    public void getValidReceipt() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        Mockito.when(bun.getName()).thenReturn("Hamburger Bun");
        Mockito.when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(sauce.getName()).thenReturn("hot sauce");
        Mockito.when(sauce.getPrice()).thenReturn(100f);
        Mockito.when(filling.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(filling.getName()).thenReturn("cutlet");
        Mockito.when(filling.getPrice()).thenReturn(50f);
        String expectedReceipt = "(==== Hamburger Bun ====)\r\n" +
                "= sauce hot sauce =\r\n" +
                "= filling cutlet =\r\n" +
                "(==== Hamburger Bun ====)\r\n" +
                "\r\n" +
                "Price: 150,000000\r\n";
        burger.setBuns(bun);
        assertEquals("Неправильный рецепт", expectedReceipt, burger.getReceipt());
    }

    @Test
    public void getSizeAddIngredients() {
        burger.addIngredient(filling);
        int expectedSize = 1;
        assertEquals("Неправильное количество ингредиентов", expectedSize, burger.ingredients.size());
    }

    @Test
    public void getSizeRemoveIngredient() {
        burger.addIngredient(sauce);
        burger.removeIngredient(0);
        assertTrue("Ингредиент не удален", burger.ingredients.isEmpty());
    }

    @Test
    public void getMovedIngredient() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        Ingredient ingredientPosition = burger.ingredients.get(1);
        burger.moveIngredient(1, 0);
        Ingredient ingredientPositionAfterMoving = burger.ingredients.get(0);
        assertEquals("Элемент не на своем месте", ingredientPositionAfterMoving, ingredientPosition);
    }
}