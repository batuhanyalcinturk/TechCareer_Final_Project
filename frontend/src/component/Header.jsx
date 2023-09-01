
// rcc
import React, { Component } from 'react'

// I18N
import { withTranslation } from 'react-i18next';
import { Link, useNavigate  } from 'react-router-dom';

// CLASS COMPONENT
class Header extends Component {

    // Component görünen ismi
    static displayName = "Todo_Header";

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
                <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                    <div className="container">
                        <a className="navbar-brand" href="#">
                            <Link className="navbar-brand" to="todos/list"><i className={this.props.logo}></i></Link>
                        </a>
                        <button
                            className="navbar-toggler d-lg-none"
                            type="button"
                            data-bs-toggle="collapse"
                            data-bs-target="#collapsibleNavId"
                            aria-controls="collapsibleNavId"
                            aria-expanded="false"
                            aria-label="Toggle navigation"
                        >
                            

                            <span className="navbar-toggler-icon" />
                        </button>
                    </div>
                </nav>


            </React.Fragment>
        ) //end return
    } //end render
} //end class

// Higher Order Component
export default withTranslation()(Header);