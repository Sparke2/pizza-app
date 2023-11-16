import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash } from "@fortawesome/free-solid-svg-icons";
import { faArrowRight } from "@fortawesome/free-solid-svg-icons";
import Footer from "../components/Footer";
import Header from "../components/Header";
import { Button } from "react-bootstrap";
import { useEffect, useState } from "react";
import img from "../img/pizza_1.png"

function Basket() {
    const [pizza, setPizza] =useState('');
    const [user, setUser] =useState('');
    useEffect(()=>{
        fetch("http://localhost:8090/pizza/findByIsBasket")
        .then(res=>res.json())
        .then((result)=>{
            setPizza(result);
        })

        fetch("http://localhost:8090/user/1")
        .then(res=>res.json())
        .then((result)=>{
            setUser(result);
        })
    },[])
    const updatePizza = id => {
        console.log(id);
        fetch("http://localhost:8090/pizza/deleteFromBasket/"+ id, {
          method: "PUT",
          headers: {"Content-Type":"application/json"},
          body:JSON.stringify(id)
        }).then(()=>{
          window.location.reload();
        })
      }
      const createOrder = () => {
        const id = 1;
        console.log(id);
        fetch("http://localhost:8090/order/placeOnOrder/"+ id, {
          method: "POST",
          headers: {"Content-Type":"application/json"},
          body:JSON.stringify(arrPizz)
        }).then(()=>{
          window.location.reload();
        })
      }

     let total = 0;
     let arrPizz = [];
     console.log(arrPizz);
     {Array.from(pizza).map(element => {
            return total = total + element.price;
      })}
      {Array.from(pizza).map(element => {
        return arrPizz.push(element.id);
     })}
    //  {Array.isArray(pizza)
    //     ? pizza.map(el => {
    //     return total = total + el.price;
    //   }) : null};
     
    return (
        <>
        <Header/>
        <div className="container py-5">
            <div className="d-flex align-ites-center justify-content-between">
                <h2 className="fw-700">Корзина</h2>
            </div>
            {Array.isArray(pizza) ? pizza.map((p, index)=>(
                
            <div className="row br-bottom mb-3" key={index}>
                <div className="col">
                    <img src={img} alt="Пицца" className="w-50 pb-3"/>
                </div>
                <div className="col">
                    <p className="fw-600">{p.name}</p>
                </div>
                <div className="col">
                    <p className="fw-600">{p.price} ₽</p>
                </div>
                <div className="col text-end">
                    <Button className="btn"><FontAwesomeIcon icon={faTrash} className="text-white" onClick={()=>updatePizza(p.id)}/></Button>
                </div>
            </div>
              )) : null}
            <p className="fw-600 pt-3">Сумма заказа: {total} ₽</p>

            <h5 className="fw-600">Укажите адрес для доставки</h5>
            <p>Саратов, ул им Разина С.Т., д.100, кв. 32, подъезд №1, этаж 3, домофон 32</p>
            <h5 className="fw-600">Способ оплаты</h5>
            <p>Картой</p>
            <Button onClick={()=>createOrder()}>Продолжить <FontAwesomeIcon icon={faArrowRight} className="text-white ps-3"/></Button>
        </div>
        <Footer/>
        </>
    );
}

export default Basket;