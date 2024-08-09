import React,{ useState , useEffect} from 'react'
import './Login.css'
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function Login(){
    const navigate = useNavigate();

    const [username,setUsername] = useState("");
    const [password,setPassword] = useState("");

    const handleSubmit = (e) =>{
        e.preventDefault();

       
        fetch("http://localhost:9494/webapi/rm/login",{
            "method":"POST",
            "headers":{
                "Accept":"*/*",
                "Content-Type":"application/json"
            },
            mode:"cors",
            body:JSON.stringify({username,password})
        })
        .then(res => res.json())
        .then((data) => {
            console.log(data);

            if(data.status === "success")
            {
                sessionStorage.setItem("rmid",data.rmid);
                sessionStorage.setItem("token",data.token);
                toast.success("Successfull Login",{
                    autoClose:500,
                    position:"top-right",
                    hideProgressBar: false,
                    closeOnClick: true
                })
                navigate("/adminhome");
            }
            else {
                toast.error("Invalid username or password",{
                    position:"top-right",
                    hideProgressBar: false,
                    closeOnClick: true
                })
                console.log("error");
            }
            
        });
        console.log("hello "+username+" "+password);
    }
    return(
        <div>
            <ToastContainer />
            <div className='head'>Login</div>
            <div className='container'>
                <form className='formcss'>
                    <div>
                        <div>
                            <label htmlFor="username" className='labeltext'>Username:</label>
                        </div>
                        <input type="text" name="username" className='inputbox' onChange={(e)=>{setUsername(e.target.value)}}/>
                    </div>
                    <div>
                        <div>
                            <label htmlFor="password" className='labeltext'>Password:</label>
                        </div>
                        <input type="password" name="password" className='inputbox' onChange={(e)=>{setPassword(e.target.value)}}/>
                    </div>
                    <div>
                        <div>
                            <input type="submit" value="Login" className='btn' onClick={handleSubmit}></input>
                        </div>
                        {/* <div className='oroption'>--------OR--------</div>
                        <div>
                            <input type="submit" value="SignUp" className='btn' onClick={()=>{navigate("/signup")}}></input>
                        </div> */}
                    </div>
                </form>
            </div>
            
            
        </div>
    );
}