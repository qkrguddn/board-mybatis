import { useState, useEffect } from "react"
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
const Main = () => {
    const [boards, setBoards] = useState([]);
    const navigate = useNavigate();
    useEffect(() => {
        axios.get("/api/board").then((response) => {
            setBoards(response.data);
        });
    }, []);
    const goDetail = (id) => {
        navigate(`/detail/${id}`);
    };
    return (
        <div>
            <Link to="/">메인화면</Link> <br></br>
            <h2 className="text-center">게시판</h2>
            <div className="row">
                <Link to="/create-board/">  <button className="btn btn-primary"> 글 작성</button></Link>
            </div>
            <div className="row">
                <table className="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>작성자 </th>
                            <th>글제목 </th>
                            <th>내용 </th>
                            <th>작성일 </th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            boards.map(
                                board =>
                                    <tr key={board.id}>
                                        <td> {board.id} </td>
                                        <td> {board.writer} </td>
                                        <td style={{ color: "blue" }} onClick={() => goDetail(board.id)}>{board.title}</td>
                                        <td> {board.content} </td>
                                        <td> {board.createdDate} </td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        </div>
    );

}

export default Main;
