from flask import *
from src.dbconnection import *
app=Flask(__name__)


@app.route("/login",methods=['post'])
def main():
    uname=request.form['uname']
    pswd=request.form['pswd']
    qry = "select * from login where USERNAME=%s and PASSWORD=%s and (type='user' or type='worker')"
    val = (uname, pswd)
    res = selectone(qry, val)
    if res is None:
        return jsonify({"task":"invalid"})
    else:
        return jsonify({"task": "valid","lid":res['LID'],"type":res['TYPE']})


@app.route("/usersignup",methods=['post'])
def usersignup():
    uname = request.form['uname']
    pswd = request.form['pswd']
    fname = request.form['fname']
    lname = request.form['lname']
    gender = request.form['gender']
    dob = request.form['dob']
    place = request.form['place']
    post = request.form['post']
    pin = request.form['pin']
    email = request.form['email']
    phone = request.form['phone']
    qry = "insert into login values(null,%s,%s,'user')"
    val = (uname,pswd)
    id = iud(qry,val)
    q = "insert into user values(null,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
    v = (str(id),fname,lname,gender,dob,place,post,pin,email,phone)
    iud(q,v)
    return jsonify({"task":"valid"})



@app.route("/workersignup",methods=['post'])
def workersignup():
    print(request.form)
    fname = request.form['fname']
    lname = request.form['lname']
    gender = request.form['gender']
    place = request.form['place']
    post = request.form['post']
    pin = request.form['pin']
    email = request.form['email']
    phone = request.form['phone']
    uname=request.form['uname']
    pswd=request.form['pswd']

    print(1)

    qry = "insert into login values(null,%s,%s,'pending')"
    val = (uname, pswd)
    id = iud(qry, val)
    q = "insert into worker values(null,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
    v = (str(id), fname, lname, gender, place, post, pin,phone, email )
    iud(q, v)
    return jsonify({"task": "valid"})





@app.route("/sendjobdetails",methods=['post'])
def sendjobdetails():
    lid=request.form['lid']
    work = request.form['work']
    details = request.form['details']
    place = request.form['place']
    post = request.form['post']
    pin = request.form['pin']
    amount = request.form['amount']
    date = request.form['date']
    time = request.form['time']
    qry = "insert into jobdetails values(null,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
    val = ( lid, work, details, place, post, pin, amount,date,time)
    iud(qry,val)
    return jsonify({"task": "valid"})


@app.route("/viewresponse", methods=['post'])
def viewresponse():
    work_id = request.form['work_id']
    q = "select * from request where work_id=%s"
    res = androidselectall(q, work_id)
    return jsonify(res)



@app.route("/workerdetails_chat", methods=['post'])
def workerdetails_chat():
    id = request.form['lid']
    q = "SELECT `worker`.*,`request`.* FROM `worker` JOIN `request` ON `worker`.`LID`=`request`.`WORKER_ID` JOIN `jobdetails`ON `jobdetails`.`ID`=`request`.`WORK_ID` WHERE `request`.`STATUS`='accepted' AND `jobdetails`.`LID`=%s"
    res = androidselectall(q,id)
    return jsonify(res)

@app.route("/jobstatus", methods=['post'])
def jobstatus():
    lid=request.form['lid']
    q = "SELECT * FROM `jobdetails` JOIN `bid` ON `jobdetails`.`ID`=`bid`.`jobid` where bid.workerid=%s"
    res = androidselectall(q,lid)
    return jsonify(res)


@app.route("/workerdetails_report", methods=['post'])
def workerdetails_report():
    id = request.form['lid']
    print(id)
    q = "SELECT `worker`.*,`request`.* FROM `worker` JOIN `request` ON `worker`.`LID`=`request`.`WORKER_ID` JOIN `jobdetails`ON `jobdetails`.`ID`=`request`.`WORK_ID` WHERE `request`.`STATUS`='accepted'"
    res = selectall(q)
    return jsonify(res)




@app.route("/viewworkdone", methods=['post'])
def viewworkdone():
    id = request.form['id']
    # q = "SELECT `worker`.`FIRSTNAME`,`LASTNAME`,`worker`.`LID` FROM `worker` JOIN `request` ON `worker`.`LID`=`request`.`WORKER_ID` JOIN `jobdetails` ON `request`.`WORK_ID`=`jobdetails`.`ID` WHERE `jobdetails`.`LID`=%s and status='accepted'"
    q = "SELECT *,worker.LID as wid FROM `bid`,`worker`,`jobdetails` WHERE `bid`.`workerid`=`worker`.`LID` AND `bid`.`status`='accepted' AND `jobdetails`.`ID`=`bid`.`jobid` AND `jobdetails`.`LID`=%s"
    res = androidselectall(q,id)
    return jsonify(res)

@app.route("/report", methods=['post'])
def report():
    from_id = request.form['from_id']
    to_id = request.form['to_id']
    date = request.form['date']
    qry = "insert into report values(null,%s,%s,%s,%s)"
    val = (id, from_id, to_id, date)
    iud(qry,val)
    return jsonify({"task":"success"})

@app.route("/reason", methods=['post'])
def complaint():
    lid = request.form['lid']
    toid = request.form['toid']
    reason = request.form['reason']
    # date = request.form['date']
    qry = "insert into report values(null,%s,%s,curdate(),%s)"
    val = (lid,toid,reason)
    iud(qry, val)
    return jsonify({"task":"success"})


@app.route("/reasonbyuser", methods=['post'])
def reasonbyuser():
    lid = request.form['lid']
    toid = request.form['toid']
    reason = request.form['reason']
    # date = request.form['date']
    qry = "insert into report values(null,%s,%s,curdate(),%s)"
    val = (lid,toid,reason)
    iud(qry, val)
    return jsonify({"task":"success"})


@app.route("/bidaccept", methods=['post'])
def bidaccept():
    ID = request.form['ID']
    qry = "update bid set status='accepted' where ID=%s"
    iud(qry, ID)
    return jsonify({"task": "success"})


@app.route("/bidreject", methods=['post'])
def bidreject():
    ID = request.form['ID']
    qry = "update bid set status='rejected' where ID=%s"
    iud(qry, ID)
    return jsonify({"task": "rejected"})


@app.route("/viewreply", methods=['post'])
def viewreply():
    lid=request.form['lid']
    q="select * from complaint where LID=%s"
    res=androidselectall(q,lid)
    return jsonify(res)



@app.route("/lowestprice", methods=['post'])
def lowestprice():
    lid=request.form['lid']
    ID=request.form['wid']
    val=(ID,lid)
    q="SELECT MIN(`request`.`AMOUNT`) as amt FROM `request` JOIN `jobdetails` ON `jobdetails`.`ID`=`request`.`WORK_ID` WHERE `request`.`WORK_ID`=%s AND `jobdetails`.`LID`=%s"
    res=selectone(q,val)
    return jsonify({'lamt':res['amt']})



@app.route("/viewworkjobprovider", methods=['post'])
def viewworkjobprovider():
    print(request.form)
    lid=request.form['lid']
    print(lid,"==============")
    q="select * from jobdetails where LID=%s"
    res=androidselectall(q,lid)
    print(res,"=============")
    return jsonify(res)

@app.route("/viewbidresult", methods=['post'])
def viewbidresult():
    jid=request.form['jobid']
    print(jid)
    # q="SELECT request.ID,`request`.`AMOUNT`,`worker`.`FIRSTNAME`,`worker`.`LASTNAME`,AVG(`rating`.`RATING`) AS rating FROM `worker` JOIN `request` ON `worker`.`LID`=`request`.`WORKER_ID` JOIN `rating` ON `rating`.`LID`=`request`.`WORKER_ID`WHERE REQUEST.STATUS='pending' GROUP BY `request`.`WORKER_ID` "
    q="SELECT AVG(`rating`.`RATING`) AS avgr,`bid`.*,bid.id AS b_id,`worker`.`FIRSTNAME`,`LASTNAME` FROM `bid` JOIN `worker` ON `worker`.`LID`=`bid`.`workerid` LEFT JOIN `rating` ON `rating`.`LID`=`worker`.`LID` WHERE `bid`.`jobid`=%s AND bid.status='pending' GROUP BY `bid`.`workerid`"
    res=selectall2(q,jid)
    print(res,"============")
    for i in res:
        if i['avgr'] is None:
            i['avgr']=0
    return jsonify(res)

@app.route("/viewworkworker", methods=['post'])
def viewworkworker():
    q="select * from work"
    res=androidselectall(q)
    return jsonify(res)

@app.route("/sendfeedback", methods=['post'])
def sendfeedback():
    lid = request.form['lid']
    feedback = request.form['feedback']
    # date = request.form['date']
    qry = "insert into feedback values(null,%s,%s,curdate())"
    val = (lid, feedback)
    iud(qry, val)
    return jsonify({"task":"success"})

# chat======================================================================================================

@app.route("/chatwithworker", methods=['post'])
def chatwithworker():
    LID = request.form['lid']
    q="SELECT `bid`.`jobid`,`bid`.`workerid`,`jobdetails`.ID,`worker`.*  FROM  bid JOIN worker ON `bid`.`workerid`=`worker`.`LID` JOIN jobdetails ON `bid`.`jobid`=`jobdetails`.`ID` WHERE `jobdetails`.LID=%s group by bid.workerid"
    res=selectall2(q,LID)
    print(res)
    return jsonify(res)


@app.route("/chatwithuser", methods=['post'])
def chatwithuser():
    LID = request.form['lid']
    q="SELECT `bid`.`jobid`,`jobdetails`.`LID`,`jobdetails`.ID,`user`.*  FROM  bid JOIN jobdetails ON `bid`.`jobid`=`jobdetails`.`ID` JOIN `user` ON `user`.`LID`=`jobdetails`.`LID` WHERE `bid`.workerid=%s GROUP BY `user`.LID "
    res=selectall2(q,LID)
    print(res)
    return jsonify(res)


@app.route('/in_message2',methods=['post'])
def in_message():
    print(request.form)
    fromid = request.form['fid']
    print("fromid",fromid)

    toid = request.form['toid']
    print("toid",toid)
    message=request.form['msg']
    print("msg",message)
    qry = "INSERT INTO `chat` VALUES(NULL,%s,%s,%s,CURDATE())"
    value = (fromid, toid, message)
    print("pppppppppppppppppp")
    print(value)
    iud(qry, value)
    return jsonify(status='send')

@app.route('/view_message2',methods=['post'])
def view_message2():
    print("wwwwwwwwwwwwwwww")
    print(request.form)
    fromid=request.form['fid']
    print(fromid)
    toid=request.form['toid']
    print(toid)
    lmid = request.form['lastmsgid']
    print("msgggggggggggggggggggggg"+lmid)
    sen_res = []
    # qry="SELECT * FROM chat WHERE (fromid=%s AND toid=%s) OR (fromid=%s AND toid=%s) ORDER BY DATE ASC"
    qry="SELECT FROM_ID as `fromid`,CHAT as `message`,DATE as `date`,ID as `msgid` FROM `chat` WHERE `ID`>%s AND ((`TO_ID`=%s AND  `FROM_ID`=%s) OR (`TO_ID`=%s AND `FROM_ID`=%s)  )  ORDER BY msgid ASC"
    val=(str(lmid),str(toid),str(fromid),str(fromid),str(toid))
    print("fffffffffffff",val)
    res = androidselectall(qry,val)
    print("resullllllllllll")
    print(res)
    if res is not None:
        return jsonify(status='ok', res1=res)
    else:
        return jsonify(status='not found')


# chat end=====================================================================================
@app.route("/jobsearch", methods=['post'])
def jobsearch():
    name = request.form['name']
    q = "SELECT * FROM `jobdetails` JOIN `user` ON `user`.`LID`=`jobdetails`.`LID` where jobdetails.WORK like '%"+name+"%'"
    res = selectall(q)
    return jsonify(res)


@app.route("/addingskills", methods=['post'])
def addingkills():
    lid=request.form['lid']
    skill = request.form['skill']
    qry = "insert into skill values(null,%s,%s)"
    val = (lid,skill)
    iud(qry, val)
    return jsonify({"task": "valid"})

@app.route("/addskills", methods=['post'])
def addskills():
    # lid=request.form['lid']
    q = "select * from skill"
    res = androidselectallnew(q)
    print(res)
    return jsonify(res)



@app.route("/requestsend", methods=['post'])
def requestsend():
    work_id = request.form['work_id']
    worker_id = request.form['worker_id']
    amount = request.form['amount']
    qry = "insert into request values(%s,,%s,%s,'pending')"
    val = (work_id, worker_id, amount)
    iud(qry, val)
    return jsonify({"task": "successfull"})


@app.route("/viewfeedback", methods=['post'])
def viewfeedback():
    q = "select * from feedback"
    res = androidselectallnew(q)
    return jsonify(res)


@app.route("/sendcomplaint", methods=['post'])
def sendcomplaint():
    lid = request.form['lid']
    complaint = request.form['complaint']
    # date = request.form['date']
    qry = "insert into complaint values(null,%s,%s,'pending',curdate())"
    val = (lid, complaint )
    iud(qry, val)
    return jsonify({"task":"success"})


@app.route("/addrating",methods=['post'])
def addrating():
    lid = request.form['worker']
    rating = request.form['rating']
    qry = "insert into rating values(null,%s,%s,curdate())"
    val = (lid, rating)
    iud(qry, val)
    return jsonify({"task": "valid"})



@app.route("/viewuser", methods=['post'])
def viewuser():
    q="select * from `user`"
    res=selectall(q)
    print(res)
    return jsonify(res)




@app.route("/viewworker", methods=['post'])
def viewworker():
    q="select * from `worker`"
    res=selectall(q)
    return jsonify(res)


@app.route("/employeeviewworkjobprovider", methods=['post'])
def employeeviewworkjobprovider():
    # name = request.form['name']
    q="SELECT * FROM `jobdetails` JOIN `user` ON `user`.`LID`=`jobdetails`.`LID` "
    res=selectall(q)
    return jsonify(res)


@app.route('/search_work', methods=['post'])
def search_work():
    work = request.form['work']
    qry = "SELECT * FROM `jobdetails` JOIN `user` ON `jobdetails`.`LID`=`user`.`LID` WHERE `jobdetails`.`WORK` LIKE  '"+str(work)+'%' " ' "

    print(qry)

    res = selectall(qry)

    print(res)

    return jsonify(res)


@app.route("/view_lowestbid", methods=['post'])
def view_lowestbid():
    jobid = request.form['jobid']
    q="SELECT * FROM `bid` WHERE `jobid`=%s ORDER BY `bidamount` ASC LIMIT 1"
    res=selectone(q,jobid)

    if res is None:
        return jsonify(data=0)
    else:
        print(res['bidamount'])
        return jsonify(data = res['bidamount'])



@app.route("/add_bidamount", methods=['post'])
def add_bidamount():
    print(request.form)
    jid = request.form['jobid']
    amt = request.form['bamt']
    lid = request.form['lid']
    iud("INSERT INTO `bid` (`jobid`,`workerid`,`bidamount`,status) VALUES(%s,%s,%s,'pending')",(str(jid),str(lid),str(amt)))
    return jsonify(data = "ok")




app.run(host="0.0.0.0",port="5000")
