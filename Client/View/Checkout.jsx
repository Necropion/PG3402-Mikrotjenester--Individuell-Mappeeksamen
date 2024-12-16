import Header from "./Components/Header";
import {useContext, useEffect, useState} from "react";
import {ApplicationContext} from "../Application";
import carImg from "../Images/car.svg";

const Checkout = () => {

    const { gateway, user, receipt } = useContext(ApplicationContext);

    const [receiptItemsList, setReceiptItemsList] = useState(null);

    const fetchReceiptItems = async () => {

        const getReceiptItems = await fetch(`${gateway}/api/receipt/items/${receipt.id}`)

        if(getReceiptItems.ok){
            const itemsList = await getReceiptItems.json();
            setReceiptItemsList(itemsList);
        }
    }

    useEffect(() => {
        fetchReceiptItems();
    }, []);

    return(
        <>
            <Header/>
            <main className={'text-center text-8xl mt-[5vh] flex flex-wrap justify-center'}>
                <div className={'bg-white rounded-xl w-[95vw] h-[82vh] flex-wrap'}>
                    <h2 className={'w-[100%] h-[10%] border-b-2 text-5xl text-center content-center'}>Thank you for your purchase!</h2>
                    <div className={'h-[80%] w-[100%] text-4xl mt-5 flex flex-wrap justify-center'}>
                        <div className={'w-[100%] h-[7%]'}>Receipt ID: {receipt.id}</div>
                        <div className={'w-[100%]  h-[7%]'}>Total: ${receipt.totalPrice}</div>
                        <div className={'w-[100%]  h-[7%]'}>Purchase Date: {receipt.purchaseDate}</div>
                        <div className={'w-[100%]  h-[7%]'}>Receipt Items:</div>
                        <div className={'w-[100%]  h-[79%] overflow-scroll border-2 border-black'}>
                            {receiptItemsList ? receiptItemsList.map((item, index) => (
                                <div key={index}
                                     className={'text-xl bg-cyan-600 text-white ml-2 mt-2 mr-2 h-[150px] border-2 border-black flex flex-wrap'}>
                                    <img id="carImg" className={'w-[25%] h-[100%] border-r-2 border-black'}
                                         src={carImg}></img>
                                    <div className={'w-[75%] h-[100%] flex flex-col justify-center'}>
                                        <div>{item.productName}</div>
                                        <div>${item.productPrice}</div>
                                        <div>Quantity: {item.quantity}</div>
                                    </div>
                                </div>
                            )) : <div>Loading</div>}
                        </div>
                    </div>
                </div>
            </main>
        </>
    )
}

export default Checkout;