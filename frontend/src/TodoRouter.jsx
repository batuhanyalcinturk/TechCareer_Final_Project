
// rcc
import React, { Component } from 'react'

// ROUTER
import { Navigate, Route, Routes } from 'react-router-dom';

// I18N
import { withTranslation } from 'react-i18next';

// HEADER,FOOTER,MAIN
import Footer from './component/Footer';
import Header from './component/Header';
import Main from './component/Main';

// CATEGORY
import TodoList from './component/todo/TodoList';
import TodoCreate from './component/todo/TodoCreate';
import TodoView from './component/todo/TodoView';
import TodoUpdate from './component/todo/TodoUpdate';


// CLASS COMPONENT
class TodoRouter extends Component {

    // Component görünen ismi
    static displayName = "Todo_Router";

    // Constructor
    constructor(props) {
        super(props);

        // STATE
        this.state = {}

        // BIND
    } //end constructor

    // CDM

    // FUNCTION

    //RENDER
    render() {

        //RETURN
        return (
            <React.Fragment>
                <Header logo="fa-solid fa-warehouse" />

                <div className="container">
                    <Routes>
                        <Route path='/' element={<Main />} />

                        {/* Todo category */}
                        <Route path='/todo/list' element={<TodoList/>} />
                        <Route path='/todo/create' element={<TodoCreate/>} />
                        <Route path='/todo/view/:id' element={<TodoView/>} />
                        <Route path='/todo/update/:id' element={<TodoUpdate/>} />
                        {/* bad request */}
                        <Route path="*" element={<Navigate to="/" />} />
                    </Routes>
                </div>

                <Footer copy="&copy; 2021 - 2023" />
            </React.Fragment>
        ) //end return
    } //end render
} //end class

// Higher Order Component
export default withTranslation()(TodoRouter);