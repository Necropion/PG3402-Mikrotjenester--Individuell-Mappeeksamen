import React, {memo, useContext} from "react";
import {ApplicationContext} from "../../Application";
import CartSelected from "./CartSelected";
import CartList from "./CartList";

const Cart = memo(({ itemList, setItemList, itemAdded }) => {

    const { cart } = useContext(ApplicationContext);

    return cart ? (
        <CartSelected itemList={itemList} setItemList={setItemList} itemAdded={itemAdded}/>
    ) : (
        <CartList itemAdded={itemAdded}/>
    )
})

export default Cart;
