import './App.css';
import 'antd/dist/antd.css'
import React, { useState } from 'react';
import RadioTextBar from './components/RadioTextBar/RadioTextBar'
import TextInput from './components/TextInput/TextInput'
import ResponseBox from './components/ResponseBox/ResponseBox'
import TitleText from './components/TitleText/TitleText';

import { Layout } from 'antd';

const { Header, Footer } = Layout;


class App extends React.Component {
  state = {
    mode: '',
    url: '',
    response: [],
  };

  render() {
    return (
      <div className="App">
        <Header id="header"></Header>
        <div className="bg">
        <br />
        <TitleText ></TitleText>
        <RadioTextBar appState={this.state}></RadioTextBar>
        <TextInput appState={this.state}></TextInput>
        <ResponseBox appState={this.state}></ResponseBox>
        </div>
      </div>
    );
  }
}

export default App;
