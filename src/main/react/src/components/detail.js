
import { useParams } from "react-router";
import { useState, useEffect } from "react";
import axios from 'axios';
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
const Detail = () => {
    let navigate = useNavigate();
    const id = useParams().boardId;
    const [boards, setBoards] = useState({});

    useEffect(() => {
        const viewNow = async () => {
            const response = await axios.get(`/api/board/${id}`)
            setBoards({
                title: response.data.title,
                createdDate: response.data.createdDate,
                content: response.data.content,
            })
        }
        viewNow()
    }, [id])

    function goEdit() {
        navigate(`/edit/${id}`);
    }
    function boardDelete() {
        axios.delete(`/api/board/${id}`)
        navigate('/');
    }
    return (
        <div>
            <Link to="/">메인화면</Link> <br></br>
            <div className="card col-md-6 offset-md-3">
                <div className="card-body">
                    <div className="row">
                        <label> 제목: {boards.title} </label>
                    </div>

                    <div className="row">
                        <label>내용 </label><br></br>
                        <textarea value={boards.content} />
                    </div >
                    <div className="buttons">
                        <button className="btn btn-danger" onClick={() => { boardDelete() }} style={{ marginLeft: "10px" }}>글 삭제</button>
                        <button className="btn btn-success" onClick={goEdit}>수정</button>
                    </div>
                </div>
            </div>
        </div>
    );  
}

export default Detail;