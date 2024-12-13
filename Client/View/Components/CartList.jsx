import {useContext, useEffect, useState} from "react";
import {ApplicationContext} from "../../Application";

const CartList = ({ setCart }) => {

    const { user, cartList, setCartList, cartDeleted, setCartDeleted, cartSelected, setCartSelected } = useContext(ApplicationContext);
    const gateway = process.env.REACT_APP_API_URL

    const fetchUserCarts = async () => {
        console.log("fetching user carts...")
        const fetchCarts = await fetch(`${gateway}/api/cart/${user.id}`)
        const carts = await fetchCarts.json();

        if(fetchCarts.ok) {
            console.log("Carts length: " + carts.length)
            if(carts.length > 0) {
                setCartList(carts)
                console.log("Carts successfully fetched!")
            }
            if (carts.length === 0) {
                setCartList(null);
                console.log("Carts successfully fetched!")
            }
        }

    }

    const handleClick = async (e) => {
        e.preventDefault();

        if(e.target.id === "deleteBtn") {
            const deleteCartById = await fetch(`${gateway}/api/cart/${e.target.dataset.cartId}`, {
                method: 'DELETE'
            })

            if (deleteCartById.ok) {
                setCartDeleted(prev => !prev)
                console.log("Cart Deleted!")
            }
        }

        if(e.target.id === "selectBtn") {
            const selectedCart = cartList.find((cart) => String(cart.id) === e.target.dataset.cartId)
            if(selectedCart) {
                setCart(selectedCart);
                setCartSelected(true);
            }
        }
    }

    useEffect(() => {
        fetchUserCarts()
    }, [cartSelected, cartDeleted]);

    return (
        <div className={'bg-white rounded-xl ml-[1vw] w-[25vw] max-h-[400px] flex flex-col justify-center'}>
            {cartList ? cartList.map((cart, index) => (
                <div key={index} className={'h-[50px] text-xl flex flex-wrap'}>
                    <div>Cart ID: {cart.id} User ID: {cart.userId}</div>
                    <button id="deleteBtn" data-cart-id={cart.id} onClick={handleClick}>Delete</button>
                    <button id="selectBtn" data-cart-id={cart.id} onClick={handleClick}>Select</button>
                </div>
            )) : <div className={'text-2xl'}>Add an item to the cart to begin.</div>}
        </div>
    )
}

export default CartList;