import Header from "./Components/Header";
import { useEffect, useState} from "react";
import Cart from "./Components/Cart";
import carImg from "../Images/car.svg"

const Home = () => {

    // Car Variables
    const [carList, setCarList] = useState([])
    const [cartOpen, setCartOpen] = useState(false);
    const [cart, setCart] = useState(null);
    const [itemAdded, setItemAdded] = useState(null)

    const fetchCars = async () => {
        const getAllCars = await fetch('/api/car')
        const allCars = await getAllCars.json();

        if(getAllCars.ok) {
            setCarList(allCars)
        }
    }

    const addItemToCart = async (e) => {
        console.log(e.target.dataset.productId)
        const postCartItem = await fetch("/api/item",{
            method: "POST",
            body: JSON.stringify({
                cartId: cart.id,
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

    const handleClick = async (e) => {
        e.preventDefault();

        if(e.target.id === "buyBtn") {
            await addItemToCart(e);
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
                                <div>{car.make} {car.model} ({car.carYear})</div>
                                <div>{car.color}</div>
                                <button data-product-id={car.productId} id="buyBtn" className={'bg-amber-950 hover:bg-green-400 w-[10%] ml-[45%] mt-8 rounded-md'}
                                        onClick={handleClick}
                                >
                                    Buy</button>
                            </div>
                        </div>
                    ))}
                </div>
                <Cart cartOpen={cartOpen}
                      setCartOpen={setCartOpen}
                      cart={cart}
                      setCart={setCart}
                      itemAdded={itemAdded}
                />
            </main>
        </>
    )
}

export default Home;