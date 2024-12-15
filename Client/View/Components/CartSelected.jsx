import {useContext, useEffect, useState} from "react";
import {ApplicationContext} from "../../Application";
import carImg from "../../Images/car.svg";

const CartSelected = ({ itemAdded }) => {

    const { gateway, cartList, cart, setCart } = useContext(ApplicationContext);

    const [itemList, setItemList] = useState([]);
    const [productList, setProductList] = useState([]);

    const fetchItems = async () => {
        if (!cart) return;

        const getCartItems = await fetch(`${gateway}/api/item/${cart.id}`)
        const cartItemList = await getCartItems.json();

        if(getCartItems.ok) {
            setItemList(cartItemList);

            const listProducts = await Promise.all(
                cartItemList.map(async (item) => {
                    const fetchProduct = await fetch(`${gateway}/api/car/${item.id.productId}`)
                    return fetchProduct.ok ? await fetchProduct.json() : null;
                })
            );

            setProductList(listProducts.filter((product) => product !== null))
        }
    }

    const handleClick = (e) => {
        e.preventDefault();
        setCart(null);
        localStorage.setItem("cart", null);
    }

    useEffect(() => {
        if (itemAdded) {
            console.log("Fetching product for added item: ", itemAdded)

            fetchItems();
        }
    }, [itemAdded]);

    useEffect(() => {
        if (cart) {
            fetchItems();
        }
    }, [cart]);

    return (
        <div className={'bg-white rounded-xl ml-[1vw] w-[25vw] max-h-[400px] flex flex-col justify-center'}>
            <h2 className={'text-2xl h-[10%]'}>CART: {cartList ? (cartList.findIndex((c) => c.id === cart.id) + 1) : "No cart"}</h2>
            <div className={'flex flex-col h-[78%] border-black border-2 overflow-scroll'}>
                {itemList.map((item, index) => (
                    <div key={index} className={'h-[50px] text-xl flex flex-wrap'}>
                        <img id="carImg" className={'w-[25%] h-[100%] border-r-2 border-black'} src={carImg}></img>
                        <div className={'w-[70%] h-[100%] flex flex-col justify-center'}>
                            {productList[index] ? (
                                <div>{productList[index].color} {productList[index].make} {productList[index].model} ({productList[index].carYear}) Qnt:{item.quantity}</div>
                            ) : (<div>Loading...</div>)}
                        </div>
                    </div>
                ))}
            </div>
            <button className={'ml-[34.5%] mr-[37%] w-[100] h-[10%] text-xl bg-cyan-600 rounded-lg hover:bg-green-400'} onClick={handleClick}>Back</button>
        </div>
    )
}

export default CartSelected;