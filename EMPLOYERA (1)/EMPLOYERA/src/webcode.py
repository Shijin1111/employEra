from flask import *
from src.dbconnection import *
app=Flask(__name__)
app.secret_key="abcd"
import functools

def login_required(func):
    @functools.wraps(func)
    def secure_function():
        if "lid" not in session:
            return render_template('index.html')
        return func()

    return secure_function


@app.route('/logout')
def logout():
    session.clear()
    return redirect('/')

@app.route("/",methods=['post','get'])
def main():
    return render_template("index.html")

@app.route("/login",methods=['post','get'])
def login():
    username=request.form["textfield"]
    password=request.form["textfield2"]
    qry="select * from login where USERNAME=%s and PASSWORD=%s"
    val=(username,password)
    res=selectone(qry,val)
    if res is None:
       return '''<script>alert("invalid username or password");window.location="/"</script>'''
    else:
        session['lid']=res['LID']
        return redirect( '/adminhome')


@app.route("/adminhome",methods=['post','get'])
@login_required
def adminhome():
    return render_template("adminindex.html")

@app.route("/viewfeedback",methods=['post','get'])
@login_required
def feedback():
    qry="SELECT login.USERNAME,feedback.FEEDBACK,feedback.DATE FROM login JOIN feedback ON login.LID=feedback.lid"
    res=selectall(qry)
    return render_template("viewfeedback.html",val=res)


@app.route("/viewuser",methods=['post','get'])
@login_required
def viewuser():
    qry="SELECT `user`.*,`login`.`TYPE`FROM `user`JOIN`login`ON `user`.`LID`=`login`.`LID` "
    res=selectall(qry)
    return render_template("view user.html",val=res)

@app.route("/block",methods=['post','get'])
@login_required
def block():
    id=request.args.get('id')
    qry="update login set TYPE='block' where LID=%s"
    val=(str(id))
    iud(qry,val)
    return '''<script>alert("user blocked"); window.location="/viewuser"</script>'''
@app.route("/unblock",methods=['post','get'])
@login_required
def unblock():
    id=request.args.get('id')
    qry="update login set TYPE='user' where LID=%s"
    val=(str(id))
    iud(qry,val)
    return '''<script>alert("user unblocked"); window.location="/viewuser"</script>'''



@app.route("/approveworker",methods=['post','get'])
@login_required
def approveworker():
    qry="SELECT `worker`.*,login.TYPE FROM `worker`JOIN `login`ON `worker`.`LID`=`login`.`LID`"
    res = selectall(qry)
    return render_template("approve worker.html",val=res)

@app.route("/accept",methods=['post','get'])
@login_required
def accept():
    id=request.args.get('id')
    qry="update login set TYPE='worker' where LID=%s"
    val=(str(id))
    iud(qry,val)
    return '''<script>alert("allowed to login as worker"); window.location="/approveworker"</script>'''


@app.route("/reject",methods=['post','get'])
@login_required
def reject():
    id=request.args.get('id')
    qry="update login set TYPE='reject' where LID=%s"
    val=(str(id))
    iud(qry,val)
    return '''<script>alert("rejected to login as worker"); window.location="/approveworker"</script>'''






app.run(debug=True)


