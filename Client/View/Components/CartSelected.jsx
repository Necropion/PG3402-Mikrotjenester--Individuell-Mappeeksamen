import {useContext, useEffect, useState} from "react";
import application, {ApplicationContext} from "../../Application";
import carImg from "../../Images/car.svg";
import {useNavigate} from "react-router-dom";

const CartSelected = ({ itemAdded }) => {

    const { gateway, user, cartList, cart, setCart, setReceipt } = useContext(ApplicationContext);
    const navigate = useNavigate();

    const [itemList, setItemList] = useState([]);
    const [productList, setProductList] = useState([]);
    const [priceTotal, setPriceTotal] = useState(0)

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

            const filteredProducts = listProducts.filter((product) => product !== null)
            setProductList(filteredProducts)

            calculateTotal(cartItemList, filteredProducts)
        }
    }

    const calculateTotal = (items, products) => {

        if (!items || !products) return;

        const total = items.reduce((sum, item, index) => {
            const product = products[index];
            return product ? sum + item.quantity * product.price : sum;
        }, 0);

        setPriceTotal(total);
    }

    const populateReceipt = async (receiptId) => {

        if(!itemList || !itemList.length === 0) {
            console.error("No items in the cart to populate the receipt");
            return;
        }

        for (let i = 0; i < itemList.length; i++) {

            const item = itemList[i];
            const product = productList[i]

            if(!product) {
                console.error(`Product details not found for item: ${item.id.productId}`)
                continue;
            }

            const addReceiptItem = await fetch(`${gateway}/api/receipt/items`, {
                method: "POST",
                body: JSON.stringify({
                    receiptId: receiptId,
                    productId: item.id.productId,
                    productName: `${product.color} ${product.make} ${product.model} ${product.carYear}`,
                    productPrice: product.price,
                    quantity: item.quantity
                }),
                headers: {
                    "Content-Type": "application/json"
                },
            });

            if(addReceiptItem.ok) {

                const addedItem = await addReceiptItem.json();
                console.log(addedItem);
            } else {
                console.error("Failed to add receipt item for product: ", item.id.productId);
            }
        }
    }

    const removePurchasedCart = async () => {

        const deletePurchasedCart = await fetch(`${gateway}/api/cart/${cart.id}`, {
            method: "DELETE"
        });

        if(deletePurchasedCart.ok) {
            console.log("Cart deleted", cart)
            setCart(null)
            localStorage.setItem("cart", null)
        }
    }

    const purchaseCart = async () => {

        const createReceipt = await fetch(`${gateway}/api/receipt`, {
            method: "POST",
            body: JSON.stringify({ userId: user.id, totalPrice: priceTotal}),
            headers: {
                "Content-Type": "application/json",
            },
        });

        if(createReceipt.ok) {

            const newReceipt = await createReceipt.json();

            setReceipt(newReceipt);
            localStorage.setItem("receipt", JSON.stringify(newReceipt))
            await populateReceipt(newReceipt.id);
            await removePurchasedCart();

            navigate("/checkout")
        }
    }

    const handleClick = async (e) => {
        e.preventDefault();

        if(e.target.id === "backBtn") {
            setCart(null);
            localStorage.setItem("cart", null);
        }

        if(e.target.id === "purchaseBtn") {
            await purchaseCart();
        }
    }

    useEffect(() => {
        if (itemAdded) {
            console.log("Fetching product for added item: ", itemAdded)

            fetchItems();
            calculateTotal();
        }
    }, [itemAdded]);

    useEffect(() => {
        if (cart) {
            fetchItems();
            calculateTotal();
        }
    }, [cart]);

    return (
        <div className={'bg-white rounded-xl ml-[1vw] w-[25vw] max-h-[400px] flex flex-col justify-center'}>
            <h2 className={'text-2xl h-[10%]'}>CART: {cartList ? (cartList.findIndex((c) => c.id === cart.id) + 1) : "No cart"}</h2>
            <div className={'flex flex-col h-[78%] border-black border-2 overflow-scroll'}>
                {itemList.map((item, index) => (
                    <div key={index} className={'h-[50px] text-base flex flex-wrap'}>
                        <img id="carImg" className={'w-[25%] h-[100%] border-r-2 border-black'} src={carImg}></img>
                        <div className={'w-[70%] h-[100%] flex flex-col justify-center'}>
                            {productList[index] ? (
                                <div>{item.quantity} x {productList[index].color} {productList[index].make} {productList[index].model} ({productList[index].carYear}) ${productList[index].price}</div>
                            ) : (<div>Loading...</div>)}
                        </div>
                    </div>
                ))}
            </div>
            <div className={'h-[20%] w-[100%] flex flex-wrap'}>
            <button id="backBtn" className={'mt-5 ml-5 w-[100] h-[60%] text-xl bg-cyan-600 rounded-lg hover:bg-green-400'} onClick={handleClick}>Back</button>
            <button id="purchaseBtn" className={' mt-5 ml-5 w-[100] h-[60%] text-xl bg-cyan-600 rounded-lg hover:bg-green-400'} onClick={handleClick}>Checkout</button><div className={'text-xl h-[60%] text-center mt-5 ml-5'}>Total: ${priceTotal}</div>
            </div>
        </div>
    )
}

export default CartSelected;