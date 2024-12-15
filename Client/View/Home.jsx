import Header from "./Components/Header";
import {useContext, useEffect, useState} from "react";
import Cart from "./Components/Cart";
import carImg from "../Images/car.svg"
import {ApplicationContext} from "../Application";

const Home = () => {

    const { gateway, user, cartList, setCartList, cart, setCart } = useContext(ApplicationContext);

    // Car Variables
    const [carList, setCarList] = useState([])
    const [itemAdded, setItemAdded] = useState(null)

    const fetchCars = async () => {
        const getAllCars = await fetch(`${gateway}/api/car`)
        const allCars = await getAllCars.json();

        if(getAllCars.ok) {
            setCarList(allCars)
        }
    }

    const addItemToCart = async (e, activeCart) => {
        setItemAdded(null);
        console.log(e.target.dataset.productId)
        const postCartItem = await fetch(`${gateway}/api/item`,{
            method: "POST",
            body: JSON.stringify({
                cartId: activeCart.id,
                productId: e.target.dataset.productId,
                quantity: 1
            }),
            headers: {
                "Content-Type": "application/json",
            },
        });
        const newCartItem = await postCartItem.json();

        if(postCartItem.ok) {
            console.log(newCartItem);
            setItemAdded(e.target.dataset.productId)
        }
    };

    const createCart = async () => {

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
            setCartList((prevCartList) => {
                const updatedCartList = prevCartList ? [...prevCartList, newCart] : [newCart];
                localStorage.setItem("cartList", JSON.stringify(updatedCartList));
                return updatedCartList;
            })
            console.log(newCart);
            return newCart;
        }

        console.log("Failed to create a new cart");
        return null;
    };

    const handleClick = async (e) => {
        e.preventDefault();

        if(e.target.id === "buyBtn") {
            let activeCart = cart

            if(!activeCart && !cartList){
                activeCart = await createCart();
            }
            if(activeCart) {
                await addItemToCart(e, activeCart);
            }
        }
    }

    useEffect(() => {
        fetchCars();
    }, []);

    return(
        <>
            <Header />
            <main className={'text-center text-8xl mt-[5vh] flex flex-wrap justify-center'}>
                <div className={'bg-white rounded-xl w-[72vw] grid grid-cols-1'}>
                    {carList.map((car, index) => (
                        <div key={index} className={'text-xl bg-cyan-600 text-white ml-2 mt-2 mr-2 h-[150px] border-2 border-black flex flex-wrap'}>
                            <img id="carImg" className={'w-[25%] h-[100%] border-r-2 border-black'} src={carImg}></img>
                            <div className={'w-[75%] h-[100%] flex flex-col justify-center'}>
                                <div>{car.productId} {car.make} {car.model} ({car.carYear})</div>
                                <div>{car.color}</div>
                                <button data-product-id={car.productId} id="buyBtn" className={'bg-amber-950 hover:bg-green-400 w-[10%] ml-[45%] mt-8 rounded-md'}
                                        onClick={handleClick}
                                >
                                    Buy</button>
                            </div>
                        </div>
                    ))}
                </div>
                <Cart itemAdded={itemAdded} setItemAdded={setItemAdded}/>
            </main>
        </>
    )
}

export default Home;