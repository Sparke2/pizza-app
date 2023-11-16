import { useEffect, useState } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Table from 'react-bootstrap/Table';
import Modal from 'react-bootstrap/Modal';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import { NavLink } from "react-router-dom";
import Header from '../components/Header';
import Footer from '../components/Footer';

function Assortment() {
  const [name, setName] =useState('');
  const [price, setPrice] =useState('');
  const [pizzza, setPizza]=useState('');
  const [category, setCategory] =useState('');
  const [description, setDescription]=useState('');
  const [img, setImg]=useState('');
  const [newName, setNewName]=useState('');
  const [newPrice, setNewPrice]=useState('');
  const [newCategory, setNewCategory] =useState('');
  const [newDescription, setNewDescription]=useState('');
  const [newImg, setNewImg]=useState('');
  const [pId, setPId] =useState(0);


  const [show, setShow] = useState(false);
  const [showSecondModal, setShowSecondModal] = useState(false);
  const [showThirdModal, setShowThirdModal] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const handleSecondModalClose = () => setShowSecondModal(false);
  const handleSecondModalShow = () => setShowSecondModal(true);
  const handleThirdModalClose = () => setShowThirdModal(false);
  const handleThirdModalShow = () => setShowThirdModal(true);

  const handleClick=(e)=>{
    const pizza={name, category, price, description, img}
    console.log(pizza)
    fetch("http://localhost:8090/pizza/createPizza",{
        method: "POST",
        headers: {"Content-Type":"application/json"},
        body:JSON.stringify(pizza)
    }).then(()=>{
      window.location.reload();
    })
  }
useEffect(()=>{
    fetch("http://localhost:8090/pizza")
    .then(res=>res.json())
    .then((result)=>{
        setPizza(result);
    })
},[])

const deletePizza = id => {
    fetch("http://localhost:8090/pizza/"+ id, {
      method: "DELETE",
    }).then(() => {  
        setPizza(values => {
        return values.filter(item => item.id !== id)
      })
    })
  }

  const updatePizza = id => {
    console.log(id, newName);
    fetch("http://localhost:8090/pizza/"+ id, {
      method: "PUT",
      headers: {"Content-Type":"application/json"},
      body:JSON.stringify({"name": newName, "category": newCategory, "price": newPrice, "description": newDescription, "img": newImg})
    }).then(()=>{
      window.location.reload();
    })
  }

  const helpEdit = (id,name,category,price,description,img) =>{
    handleSecondModalShow();  
    setPId(id);
    setNewName(name);
    setNewCategory(category);
    setNewPrice(price);
    setNewDescription(description);
    setNewImg(img);
  }
  const helpDelete = id =>{
    handleShow();
    setPId(id);
  }

  return (
    <>
    <Header/>
    <div className='container py-5'>
    <div className='row'>
        <div className='col-lg-3'>
          <div className="p-4 bg-grey-light br-10">
            <div className="d-flex flex-column text-center">
              <h5 className="fw-700 pb-4 fw-600">Панель администратора</h5>
              <div className="mb-3 py-1 bg-grey-light-1 active br-100 fw-600"><NavLink to={'/admin/'}>Ассортимент</NavLink></div>
              <div className="mb-3 py-1 bg-grey-light-1 br-100 fw-600"><NavLink to={'/admin/users'}>Пользователи</NavLink></div>
              <div className="mb-3 py-1 bg-grey-light-1 br-100 fw-600"><NavLink to={'/admin/orders'}>Заказы</NavLink></div>
            </div>
          </div>
        </div>
        <div className='col-lg-9'>
          <div className="p-4 bg-grey-light br-10">
            <h2 className='fw-700'>Ассортимент</h2>
            <div className='py-3'>
              <Table striped bordered hover>
                      <thead>
                          <tr>
                          <th>#ID</th>
                          <th>Название</th>
                          <th>Категория</th>
                          <th>Цена</th>
                          <th>Описание</th>
                          <th>Картинка</th>
                          <th>Действия</th>
                          </tr>
                      </thead>
                      <tbody>
                          {Array.isArray(pizzza) ? pizzza.map((p, index)=>(
                              <tr key={index}>
                              <td>{p.id}</td>
                              <td>{p.name}</td>
                              <td>{p.category}</td>
                              <td>{p.price}</td>
                              <td>{p.description}</td>
                              <td>{p.img}</td>
                              <td>
                                <Button className='btn-transperent' onClick={()=> helpEdit(p.id, p.name, p.category, p.price, p.description, p.img)}><FontAwesomeIcon icon={faPenToSquare} /></Button>
                                <Button className='me-4 btn-transperent' onClick={()=>helpDelete(p.id)}><FontAwesomeIcon icon={faTrash}/></Button>
                              </td>
                              </tr>
                          )) : null}
                      </tbody>
              </Table>
              <Button variant="secondary" className='w-100 btn-transperent br-secondary br-100 text-black fw-700' onClick={handleThirdModalShow}>Добавить пиццу <FontAwesomeIcon icon={faPlus} /></Button>     
            </div>
          </div>
        </div>
      </div>
      <Modal show={show} onHide={handleClose}>
                  <Modal.Header closeButton>
                    <Modal.Title>Вы действительно хотите удалить пиццу?</Modal.Title>
                  </Modal.Header>
                  <Modal.Body>Очень жалко, если она будет удалена</Modal.Body>
                  <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                      Отмена
                    </Button>
                    <Button variant="primary" onClick={() => {handleClose(); deletePizza(pId);}}>
                      Удалить
                    </Button>
                  </Modal.Footer>
            </Modal>
            <Modal show={showSecondModal} onHide={handleSecondModalClose}>
                  <Modal.Header closeButton>
                    <Modal.Title>Изменить пиццу</Modal.Title>
                  </Modal.Header>
                  <Modal.Body>
                    <label htmlFor="nameRew" className="form-label">Имя</label>
                    <input value={newName} className="form-control" id="nameRew" onChange={e => setNewName(e.target.value)} />
                    <label htmlFor="newCategory" className="form-label">Категория</label>
                    <input value={newCategory} type="text" className="form-control" id="newCategory" required onChange={e => setNewCategory(e.target.value)} />
                    <label htmlFor="rewText" className="form-label">Цена</label>
                    <input value={newPrice} type="text" className="form-control" id="rewText" required onChange={e => setNewPrice(e.target.value)} />
                    <label htmlFor="newDescription" className="form-label">Описание</label>
                    <input value={newDescription} type="text" className="form-control" id="newDescription" required onChange={e => setNewDescription(e.target.value)} />
                    <label htmlFor="newImg" className="form-label">Картинка</label>
                    <input value={newImg} type="text" className="form-control" id="newImg" required onChange={e => setNewImg(e.target.value)} />
                  </Modal.Body>
                  <Modal.Footer>
                    <Button variant="secondary" onClick={handleSecondModalClose}>
                      Отмена
                    </Button>
                    <Button variant="primary" onClick={() => {handleSecondModalClose(); updatePizza(pId)}}>
                      Изменить
                    </Button>
                  </Modal.Footer>
              </Modal>
              <Modal show={showThirdModal} onHide={handleThirdModalClose}>
                  <Modal.Header closeButton>
                    <Modal.Title>Добавить пиццу</Modal.Title>
                  </Modal.Header>
                  <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="name">
                            <Form.Label>Название</Form.Label>
                            <Form.Control type="text" value={name} onChange={(e)=>setName(e.target.value)} />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="price">
                            <Form.Label>Цена</Form.Label>
                            <Form.Control type="number" value={price} onChange={(e)=>setPrice(e.target.value)} />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="price">
                            <Form.Label>Категория</Form.Label>
                            <Form.Control type="text" value={category} onChange={(e)=>setCategory(e.target.value)} />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="price">
                            <Form.Label>Описание</Form.Label>
                            <Form.Control type="text" value={description} onChange={(e)=>setDescription(e.target.value)} />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="price">
                            <Form.Label>Картинка</Form.Label>
                            <Form.Control type="text" value={img} onChange={(e)=>setImg(e.target.value)} />
                        </Form.Group>
                    </Form>
                  </Modal.Body>
                  <Modal.Footer>
                    <Button variant="secondary" onClick={handleThirdModalClose}>
                      Отмена
                    </Button>
                    <Button variant="primary" onClick={() => {handleThirdModalClose(); handleClick()}}>
                      Добавить
                    </Button>
                  </Modal.Footer>
              </Modal>
    </div>
    <Footer/>
    </>
  );
}

export default Assortment;