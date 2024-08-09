import { useState , useEffect} from 'react';
import './AdminHome.css'

const AdminHome = () => {
   
  const [userStatusList, setUserStatusList] = useState([]);

  const fetchitems = async () => {
      const rmid = parseInt(sessionStorage.getItem("rmid"),10);
      const token = sessionStorage.getItem("token");

      console.log("Admin mei "+rmid+" "+token);
      //rmid = 1;
      try{
        const res = await fetch("http://localhost:9494/webapi/rm/getUserStatus",{
          method:'POST',
          headers:{
            "Content-Type":"application/json",
            //"token":"B3e1OVYwuIRICfON"
            "token":token
          },
          mode:"cors",
          body:JSON.stringify({rmid})
        })
        const data = await res.json();

        if(data.status === "success")
          setUserStatusList(data.userStatus);

      }
      catch(error)
      {
        console.log(error);
      }
    }
      

    useEffect(() => {
      fetchitems();
    }, []);

  return (
    <div className='outer-container'>
    <div className='containera'>
      <div className='header'>RM Portal</div>
        <table className='customer-list'>
            <tr className="customer-card">
                <th className='info'><b>User Id</b></th>
                <th className='info'><b>Username</b></th>
                <th className='info'><b>Application Status</b></th>
                <th className='info'><b>Admin Stage</b></th>
            </tr>
            {
                userStatusList.map((userStatus,index) => {
                    return(
                        <tr className="customer-card" key={index}>
                            <td className='info'>{userStatus.userid}</td>
                            <td className='info'>{userStatus.username}</td>
                            <td className='info'>{userStatus.applStatus} Completed</td>
                            <td className='info'>{userStatus.adminStage ? userStatus.adminStage : "Not assigned"}</td>
                        </tr>
                    )
                })
            }
        </table>
    </div>
    </div>
  )
}

export default AdminHome
