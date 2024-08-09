import AdminHome from "./Components/AdminHome";
import Login from "./Components/Login";
import {BrowserRouter,Route,Routes} from 'react-router-dom'
function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route index element={<Login/>} />
          <Route exact path="/adminhome" element={<AdminHome/>}/>
        </Routes>
      </BrowserRouter>
      
    </>
  );
}

export default App;
