import Header from "../components/Header";
import Footer from "../components/Footer";
import { useEffect, useState } from "react";

function Profile() {
    const [order, setOrder] = useState('');
    // const [pizza, setPizza] = useState('');

    useEffect(()=>{
        fetch("http://localhost:8090/order")
        .then(res=>res.json())
        .then((result)=>{
            setOrder(result);
        })
    },[])
    // useEffect(()=>{
    //     fetch("http://localhost:8080/pizza")
    //     .then(res=>res.json())
    //     .then((result)=>{
    //         setPizza(result);
    //     })
    // },[])
    return (
        <>
            <Header/>
            <div className="container py-5">
                <h3 className="fw-700 mb-5">Привет, Мария !</h3>
                <h4 className="pb-4">Заказы</h4>
                {Array.isArray(order) ? order.map((p, index)=>(
                p.userId===1?
                <div className="row br-bottom" key={index}>
                    <div className="col">
                        <p className="fw-600 m-0">#{p.id}</p>
                        <div className="badge orange mb-2">{p.status}</div>
                    </div>
                    <div className="col">
                        <p className="fw-600 m-0">Адрес</p>
                        <p>Саратов, ул им Разина С.Т., д.100, кв. 32, подъезд №1, этаж 3, домофон 32</p>
                    </div>
                    
                    <div className="col">
                        <p className="fw-600 m-0">Стоимость</p>
                        <p>{p.price} ₽</p>
                    </div>
                    <div className="col">
                        <p className="fw-600 m-0">Заказ</p>
                        {Array.isArray(p.items) ? p.items.map((i, ind)=>(
                            <p key={ind}>{i.pizzaId}</p>
                        )) : null}
                    </div>
                </div> : ''
                 )) : null}
            </div>
            <Footer/>
        </>
    );
}

export default Profile;