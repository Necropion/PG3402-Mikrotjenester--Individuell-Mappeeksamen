import {useContext, useEffect, useState} from "react";
import {ApplicationContext} from "../../Application";

const CartList = () => {

    const { gateway, user, cartList, setCartList,  setCart, cartDeleted, setCartDeleted } = useContext(ApplicationContext);

    const fetchUserCarts = async () => {
        console.log("fetching user carts...")
        const fetchCarts = await fetch(`${gateway}/api/cart/${user.id}`)
        const carts = await fetchCarts.json();

        if(fetchCarts.ok) {
            console.log("Carts length: " + carts.length)
            if(carts.length > 0) {
                setCartList(carts)
                localStorage.setItem("cartList", JSON.stringify(carts))
                console.log("Carts successfully fetched!")
            }
            if (carts.length === 0) {
                setCartList(null);
                localStorage.setItem("cartList", null)
                console.log("Carts successfully fetched!")
            }
        }
    }

    const handleClick = async (e) => {
        e.preventDefault();

        if(e.target.id === "createBtn") {
            const createCart = await fetch(`${gateway}/api/cart`, {
                method: "POST",
                body: JSON.stringify({userId: user.id}),
                headers: {
                    "Content-Type": "application/json",
                },
            });
            const newCart = await createCart.json();

            if(createCart.ok) {
                setCart(newCart);
                localStorage.setItem("cart", JSON.stringify(newCart))
                setCartList((prevCartList) => (prevCartList ? [...prevCartList, newCart] : [newCart]))
                console.log(newCart);
            }

            console.log("Failed to create a new cart");
        }

        if(e.target.id === "deleteBtn") {
            const deleteCartById = await fetch(`${gateway}/api/cart/${e.target.dataset.cartId}`, {
                method: 'DELETE'
            })

            if (deleteCartById.ok) {
                setCart(null);
                localStorage.setItem("cart", null)
                setCartDeleted(prev => !prev)
                console.log("Cart Deleted!")
            }
        }

        if(e.target.id === "selectBtn") {
            const selectedCart = cartList.find((cart) => String(cart.id) === e.target.dataset.cartId)
            if(selectedCart) {
                setCart(selectedCart);
                localStorage.setItem("cart", JSON.stringify(selectedCart))
            }
        }
    }

    useEffect(() => {
        fetchUserCarts()
    }, [cartDeleted]);

    return (
        <div className={'bg-white rounded-xl max-h-[400px] flex flex-col justify-center'}>
            <div className={'flex-1 h-[80%]'}>{cartList ? cartList.map((cart, index) => (
                <div key={index} className={'h-[50px] text-xl flex flex-wrap content-center'}>
                    <div className={'pl-5 w-[55%]'}>Cart {index + 1}</div>
                    <button id="deleteBtn" data-cart-id={cart.id} onClick={handleClick} className={'w-[20%]'}>Delete</button>
                    <button id="selectBtn" data-cart-id={cart.id} onClick={handleClick} className={'w-[20%]'}>Select</button>
                </div>
            )) : <div className={'text-2xl pt-[35%]'}>Add an item to the cart to begin.</div>}
            </div>
            {cartList ? <button id="createBtn" onClick={handleClick} className={'text-2xl'}>New Cart</button> : <div></div>}
        </div>
    )
}

export default CartList;