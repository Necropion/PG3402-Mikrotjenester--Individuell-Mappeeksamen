import {useContext, useEffect, useState} from "react";
import {ApplicationContext} from "../../Application";
import carImg from "../../Images/car.svg";

const Cart = ({ cartOpen, setCartOpen, cart, setCart, itemAdded }) => {

    const { user } = useContext(ApplicationContext);

    const [itemList, setItemList] = useState([])

    const fetchItems = async () => {
        const getCartItems = await fetch("/api/item/" + cart.id)
        const cartItemList = await getCartItems.json();

        if(getCartItems.ok) {
          setItemList(cartItemList);
          console.log("Response List:", cartItemList);
          console.log("Pretty JSON:" + JSON.stringify(cartItemList, null, 2));
          console.log(itemList)
        }
    }

    const handleClick = async () => {
        const createCart = await fetch("/api/cart", {
            method: "POST",
            body: JSON.stringify({user_id: user.id}),
            headers: {
                "Content-Type": "application/json",
            },
        });
        const newCart = await createCart.json();

        if(createCart.ok) {
            setCartOpen(true)
            setCart(newCart)
            console.log(newCart)
        }
    };

    useEffect(() => {
        console.log("UseEffect Triggered!")
        if(cart) {
            fetchItems();
        }
    }, [cart, itemAdded]);

    return (
        <div className={'bg-white rounded-xl ml-[1vw] w-[25vw] max-h-[400px] flex flex-col justify-center'}>
            {(cartOpen === true) ? <h2 className={'text-2xl h-[10%]'}>CART</h2> : <div className={'h-[10%]'}></div>}
            <div className={'flex flex-col h-[78%] border-black border-2'}>
                {itemList.map((item, index) => (
                    <div key={index} className={'h-[50px] text-xl flex flex-wrap'}>
                        <img id="carImg" className={'w-[25%] h-[100%] border-r-2 border-black'} src={carImg}></img>
                        <div className={'w-[75%] h-[100%] flex flex-col justify-center'}>
                            <div>{item.id.product_id} Qnt:{item.quantity}</div>
                            <div></div>
                        </div>
                    </div>
                ))}
            </div>
            <button className={'ml-[34.5%] mr-[37%] w-[100] h-[10%] text-xl bg-cyan-600 rounded-lg hover:bg-green-400'}
                    onClick={handleClick}>
                Open Cart
            </button>
        </div>
    )
}

export default Cart;