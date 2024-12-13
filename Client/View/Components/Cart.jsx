import {useContext, useEffect, useState} from "react";
import {ApplicationContext} from "../../Application";
import carImg from "../../Images/car.svg";
import CartList from "./CartList";

const Cart = ({ cartOpen, setCartOpen, cart, setCart, itemAdded }) => {

    const { user } = useContext(ApplicationContext);
    const gateway = process.env.REACT_APP_API_URL;

    const [itemList, setItemList] = useState([]);
    const [productList, setProductList] = useState([]);

    const fetchItems = async () => {
        const getCartItems = await fetch(`${gateway}/api/item/${cart.id}`)
        const cartItemList = await getCartItems.json();

        if(getCartItems.ok) {
            setItemList(cartItemList);
        }
    }

    const fetchProducts = async (productId) => {

        try {
            const fetchProduct = await fetch(`${gateway}/api/car/${productId}`)
            const product = await fetchProduct.json();

            if (fetchProduct.ok) {
                setProductList((prev) => [...prev, product])

            }
        } catch (error) {
            console.error("Failed to fetch products", error)
        }

        console.log("I worked!", productList)
    }

    useEffect(() => {
        console.log("UseEffect Triggered!", productList)
        if(cart) {
            fetchItems();
        }
    }, [cart, itemAdded]);

    useEffect(() => {
        if (itemAdded) {
            console.log("Fetching product for itemAdded:", itemAdded);
            fetchProducts(itemAdded)
        }
    }, [itemAdded]);

    return (
        <div className={'bg-white rounded-xl ml-[1vw] w-[25vw] max-h-[400px] flex flex-col justify-center'}>
            {(cartOpen === true) ? <h2 className={'text-2xl h-[10%]'}>CART</h2> : <div className={'h-[10%]'}></div>}
            <div className={'flex flex-col h-[78%] border-black border-2 overflow-scroll'}>
                {itemList.map((item, index) => (
                    <div key={index} className={'h-[50px] text-xl flex flex-wrap'}>
                        <img id="carImg" className={'w-[25%] h-[100%] border-r-2 border-black'} src={carImg}></img>
                        <div className={'w-[75%] h-[100%] flex flex-col justify-center'}>
                            {productList[index] ? (
                                <div>{productList[index].color} {productList[index].make} {productList[index].model} ({productList[index].carYear}) Qnt:{item.quantity}</div>
                            ) : (<div></div>)}
                        </div>
                    </div>
                ))}
            </div>
            <button className={'ml-[34.5%] mr-[37%] w-[100] h-[10%] text-xl bg-cyan-600 rounded-lg hover:bg-green-400'}
                    onClick={handleClick}>
                Open Cart
            </button>
            <CartList />
        </div>
    )
}

export default Cart;