import { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Table from 'react-bootstrap/Table';
import Modal from 'react-bootstrap/Modal';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { NavLink } from "react-router-dom";
import Header from '../components/Header';
import Footer from '../components/Footer';

function Users() {
  const [user, setUser]=useState('');
  const [newName, setNewName]=useState('');
  const [newPhone, setNewPhone]=useState('');
  const [newAddress, setNewAddress]=useState('');
  const [pId, setPId] =useState(0);

  const [showSecondModal, setShowSecondModal] = useState(false);
  const handleSecondModalClose = () => setShowSecondModal(false);
  const handleSecondModalShow = () => setShowSecondModal(true);


useEffect(()=>{
    fetch("http://localhost:8090/user")
    .then(res=>res.json())
    .then((result)=>{
        setUser(result);
    })
},[])

  const updateUser = id => {
    fetch("http://localhost:8090/user/"+ id, {
      method: "PUT",
      headers: {"Content-Type":"application/json"},
      body:JSON.stringify({ name: newName, phone: newPhone, address: newAddress })
    }).then(()=>{
      window.location.reload();
    })
  }

  const helpEdit = (id,name,phone,address) =>{
    handleSecondModalShow();  
    setPId(id);
    setNewName(name);
    setNewPhone(phone);
    setNewAddress(address);
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
              <div className="mb-3 py-1 bg-grey-light-1 br-100 fw-600"><NavLink to={'/admin/'}>Ассортимент</NavLink></div>
              <div className="mb-3 py-1 bg-grey-light-1 br-100 fw-600 active"><NavLink to={'/admin/users'}>Пользователи</NavLink></div>
              <div className="mb-3 py-1 bg-grey-light-1 br-100 fw-600"><NavLink to={'/admin/orders'}>Заказы</NavLink></div>
            </div>
          </div>
        </div>
        <div className='col-lg-9'>
          <div className="p-4 bg-grey-light br-10">
            <h2 className='fw-700'>Пользователи</h2>
            <div className='py-3'>
              <Table striped bordered hover>
                      <thead>
                          <tr>
                          <th>#ID</th>
                          <th>Имя</th>
                          <th>Телефон</th>
                          <th>Адрес</th>
                          <th>Действия</th>
                          </tr>
                      </thead>
                      <tbody>
                          {Array.isArray(user) ? user.map((p, index)=>(
                              <tr key={index}>
                              <td>{p.id}</td>
                              <td>{p.name}</td>
                              <td>{p.phone}</td>
                              <td>{p.address}</td>
                              <td>
                                <Button className='btn-transperent' onClick={()=> helpEdit(p.id, p.name, p.phone, p.address)}><FontAwesomeIcon icon={faPenToSquare} /></Button>
                              </td>
                              </tr>
                          )) : null}
                      </tbody>
              </Table>   
            </div>
          </div>
        </div>
      </div>
            <Modal show={showSecondModal} onHide={handleSecondModalClose}>
                  <Modal.Header closeButton>
                    <Modal.Title>Изменить пользователя</Modal.Title>
                  </Modal.Header>
                  <Modal.Body>
                    <label htmlFor="nameRew" className="form-label">Имя</label>
                    <input value={newName} className="form-control" id="nameRew" onChange={e => setNewName(e.target.value)} />
                    <label htmlFor="rewText" className="form-label">Телефон</label>
                    <input value={newPhone} type="text" className="form-control" id="rewText" required onChange={e => setNewPhone(e.target.value)} />
                    <label htmlFor="rewAddress" className="form-label">Адрес</label>
                    <input value={newAddress} type="text" className="form-control" id="rewAddress" required onChange={e => setNewAddress(e.target.value)} /> 
                  </Modal.Body>
                  <Modal.Footer>
                    <Button variant="secondary" onClick={handleSecondModalClose}>
                      Отмена
                    </Button>
                    <Button variant="primary" onClick={() => {handleSecondModalClose(); updateUser(pId)}}>
                      Изменить
                    </Button>
                  </Modal.Footer>
              </Modal>
    </div>
    <Footer/>
    </>
  );
}

export default Users;