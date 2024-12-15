import React, {memo, useContext} from "react";
import {ApplicationContext} from "../../Application";
import CartSelected from "./CartSelected";
import CartList from "./CartList";

const Cart = memo(({ itemAdded }) => {

    const { cart } = useContext(ApplicationContext);

    return cart ? (
        <CartSelected itemAdded={itemAdded}/>
    ) : (
        <CartList itemAdded={itemAdded}/>
    )
})

export default Cart;
