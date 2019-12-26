1. Given a empty cart file

   when system load cart file

   then should return a empty Cart

2. Given a cart file has a line code

   when system load cart file

   then should return a Cart with a Item

3. Given a cart file has a line code with num

   when system load cart file

   then should return a Cart with a Item and has num

4. Given a promotion file has a line code

   when system load promotion file

   then should return a Promotion with a Item

5. Given a discount_promotion file has a discount_promotion line code

   when system load discount_promotion file

   then should return a Promotion with a Item

6. Given one item

   when calculate promotion

   then should return a best promotion price

7. Given a Cart has three item in promotion

   when calculate promotion

   then should return a best promotion price

8. Given a Cart and a store with name

   when checkout

   then the checkout list should include store name

9. Given a Cart

   when checkout

   then the checkout list should include checkout time

10. Given a Cart with items

    when checkout

    then the checkout list should include items'num, price, unit, total

11. Given a Cart with items

    when checkout

    then the checkout list should include beforeDiscountPrice, afterDiscountPrice, differencePrice