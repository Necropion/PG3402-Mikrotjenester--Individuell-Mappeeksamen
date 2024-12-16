import Header from "./Components/Header";
import {useContext, useEffect, useState} from "react";
import {ApplicationContext} from "../Application";
import {useNavigate} from "react-router-dom";

const Profile = () => {

    const { gateway, user, receipt, setReceipt } = useContext(ApplicationContext);
    const navigate = useNavigate();

    const [receiptList, setReceiptList] = useState(null);

    const getUserReceipts = async () => {

        const userReceiptList = await fetch(`${gateway}/api/receipt/${user.id}`)

        if (userReceiptList.ok) {
            const listReceipts = await userReceiptList.json();

            setReceiptList(listReceipts);
        }
    }

    const handleClick = (e) => {
        e.preventDefault();

        if(e.currentTarget.id === "receiptBtn") {
            setReceipt(receiptList[e.currentTarget.dataset.index]);
            localStorage.setItem("receipt", JSON.stringify(receipt));
            navigate("/checkout");
        }
    }

    useEffect(() => {
        getUserReceipts();
    }, []);

    return(
        <>
            <Header/>
            <main className={'text-center text-8xl mt-[5vh] flex flex-wrap justify-center'}>
                <div className={'bg-white rounded-xl w-[95vw] h-[82vh] flex flex-wrap'}>
                    <h2 className={'w-[100%] h-[20%] border-b-2 text-5xl text-center content-center'}>Welcome {user.username}!</h2>
                    <div className={'h-[7%] w-[50%] text-4xl mt-5 justify-center'}>Your receipts:</div>
                    <div className={'h-[7%] w-[50%] text-4xl mt-5 justify-center'}>Owned Cars:</div>
                    <div className={'w-[50%] h-[69%] grid grid-cols-2 overflow-scroll border-2 border-black'}>
                        {receiptList ? receiptList.map((receipt, index) => {

                            const totalItems = receipt.receiptItems.reduce((sum, item) => sum + item.quantity, 0);

                            return (
                            <div id="receiptBtn" data-index={index} key={index}
                                 className={'h-[250px] rounded-xl bg-purple-400 ml-2 mt-2 hover:border-black hover:border-2'}
                                 onClick={handleClick}>
                                <div className={'text-2xl'}>Receipt ID: {receipt.id}</div>
                                <div className={'mt-5 text-xl'}>Purchase Date: {receipt.purchaseDate}</div>
                                <div className={'mt-5 text-xl'}>Total: ${receipt.totalPrice}</div>
                                <div className={'mt-5 text-xl'}>Item Amount: {totalItems}</div>
                            </div>
                        )}) : <div>Loading</div>}
                    </div>
                    <div className={'w-[50%] h-[69%] grid grid-cols-2 overflow-scroll border-2 border-black'}>
                        {<div>Loading</div>}
                    </div>
                </div>
            </main>
        </>
    )
}

export default Profile;