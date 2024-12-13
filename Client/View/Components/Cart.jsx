import React, {memo, useContext} from "react";
import {ApplicationContext} from "../../Application";
import CartSelected from "./CartSelected";
import CartList from "./CartList";

const Cart = memo(({ cart, setCart, itemAdded}) => {

    const { cartSelected } = useContext(ApplicationContext);

    return cartSelected ? (
        <CartSelected cart={cart} setCart={setCart} itemAdded={itemAdded}/>
    ) : (
        <CartList cart={cart} setCart={setCart} itemAdded={itemAdded}/>
    )
})

export default Cart;
