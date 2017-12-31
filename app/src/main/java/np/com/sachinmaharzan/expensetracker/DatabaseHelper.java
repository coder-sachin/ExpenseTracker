package np.com.sachinmaharzan.expensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by lazyboy on 8/25/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    static String name="expensedb";
    static int version=1;

    String createTableSql="CREATE TABLE if not exists `user` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`username`\tTEXT NOT NULL UNIQUE,\n" +
            "\t`email`\tTEXT NOT NULL UNIQUE,\n" +
            "\t`password`\tTEXT NOT NULL\n" +
            ");";

    String createExpenseTableSql="CREATE TABLE if not exists `expense` (\n" +
            "\t`expense_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`expense_detail`\tTEXT NOT NULL,\n" +
            "\t`expense_amt`\tINTEGER NOT NULL\n" +
            ");";

    String createIncomeTableSql="CREATE TABLE if not exists `income` (\n" +
            "\t`income_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`income_desc`\tTEXT NOT NULL,\n" +
            "\t`income_amt`\tINTEGER NOT NULL\n" +
            ")";

    String createCreditTableSql="CREATE TABLE if not exists `credit` (\n" +
            "\t`cr_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`cr_amt`\tINTEGER NOT NULL,\n" +
            "\t`cr_desc`\tTEXT NOT NULL,\n" +
            "\t`cr_to`\tTEXT NOT NULL\n" +
            ");";

    String createDebitTableSql="CREATE TABLE if not exists `debit` (\n" +
            "\t`db_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`db_amt`\tINTEGER NOT NULL,\n" +
            "\t`db_desc`\tTEXT NOT NULL,\n" +
            "\t`db_from`\tTEXT NOT NULL\n" +
            ");";

    String createGroupTablesql="CREATE TABLE if not exists `grup` (\n" +
            "\t`g_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`g_name`\tTEXT NOT NULL\n" +
            ");";

    String createMemberTableSql="CREATE TABLE if not exists `member` (\n" +
            "\t`m_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`g_id`\tINTEGER,\n" +
            "\t`m_name`\tTEXT NOT NULL,\n" +
            "\tFOREIGN KEY(`g_id`) REFERENCES grup\n" +
            ");";
    String createGexpenseTableSql="CREATE TABLE if not exists`gexpense` (\n" +
            "\t`gexpense_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`m_id`\tINTEGER,\n" +
            "\t`g_id`\tINTEGER,\n" +
            "\t`gexpense_amt`\tINTEGER NOT NULL,\n" +
            "\t`gexpense_desc`\tTEXT NOT NULL,\n" +
            "\tFOREIGN KEY(`m_id`) REFERENCES member,\n" +
            "\tFOREIGN KEY(`g_id`) REFERENCES grup\n" +
            ");";
    String createGbudgetTableSql="CREATE TABLE if not exists `gbudget` (\n" +
            "\t`budget_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`m_id`\tINTEGER,\n" +
            "\t`g_id`\tINTEGER,\n" +
            "\t`budget_amt`\tINTEGER,\n" +
            "\tFOREIGN KEY(`m_id`) REFERENCES member,\n" +
            "\tFOREIGN KEY(`g_id`) REFERENCES member\n" +
            ");";
    String createGbudgetExpenseTableSql="CREATE TABLE if not exists `gbexpense` (\n" +
            "\t`gbexpense_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`g_id`\tINTEGER,\n" +
            "\t`gbexpense_amt`\tINTEGER NOT NULL,\n" +
            "\t`gbexpense_desc`\tTEXT NOT NULL,\n" +
            "\tFOREIGN KEY(`g_id`) REFERENCES grup\n" +
            ");";

    public DatabaseHelper(Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createTableSql);
        getWritableDatabase().execSQL(createExpenseTableSql);
        getWritableDatabase().execSQL(createIncomeTableSql);
        getWritableDatabase().execSQL(createCreditTableSql);
        getWritableDatabase().execSQL(createDebitTableSql);
        getWritableDatabase().execSQL(createGroupTablesql);
        getWritableDatabase().execSQL(createMemberTableSql);
        getWritableDatabase().execSQL(createGexpenseTableSql);
        getWritableDatabase().execSQL(createGbudgetTableSql);
        getWritableDatabase().execSQL(createGbudgetExpenseTableSql);

    }

    public void insertExpense(ContentValues cv){

        getWritableDatabase().insert("expense","",cv);
    }

    public void insertIncome(ContentValues cv){

        getWritableDatabase().insert("income","",cv);
    }

    public void insertCredit(ContentValues cv){

        getWritableDatabase().insert("credit","",cv);
    }

    public void insertDebit(ContentValues cv){

        getWritableDatabase().insert("debit","",cv);
    }

    public void insertGbudget(ContentValues cv){

        getWritableDatabase().insert("Gbudget","",cv);
    }

    public void insertMember(ContentValues cv){
        getWritableDatabase().insert("member","",cv);
    }
    public int getExpenseSum(){
        String expenseTotalSql="SELECT * FROM `expense`";
        Cursor c=getReadableDatabase().rawQuery(expenseTotalSql,null);
        int total=0;
        while(c.moveToNext()){
            Expense e=new Expense();
            e.expense_detail=c.getString(c.getColumnIndex("expense_detail"));
            e.expense_amt=Integer.parseInt(c.getString(c.getColumnIndex("expense_amt")));
            total=total+e.expense_amt;
        }
        c.close();
        return total;

    }

    public ArrayList<Expense> getExpenseList(){
        ArrayList<Expense>list= new ArrayList<Expense>();
        String getExpenseListSql="SELECT * FROM `expense`";
        Cursor c=getReadableDatabase().rawQuery(getExpenseListSql,null);
        while(c.moveToNext()){

            Expense e=new Expense();
            e.expense_id=Integer.parseInt(c.getString(c.getColumnIndex("expense_id")));
            e.expense_detail=c.getString(c.getColumnIndex("expense_detail"));
            e.expense_amt=Integer.parseInt(c.getString(c.getColumnIndex("expense_amt")));
            list.add(e);
        }
        c.close();
        return list;
    }
/*
    public ArrayList<Gexpense> getGexpenseList(int id){
        ArrayList<Gexpense>list= new ArrayList<Gexpense>();
        String getExpenseListSql="SELECT * FROM `gexpense` WHERE g_id="+id;
        Cursor c=getReadableDatabase().rawQuery(getExpenseListSql,null);
        while(c.moveToNext()){

            Gexpense ge=new Gexpense();
            ge.gexpense_id=Integer.parseInt(c.getString(c.getColumnIndex("gexpense_id")));
            ge.m_id=Integer.parseInt(c.getString(c.getColumnIndex("m_id")));
            ge.g_id=Integer.parseInt(c.getString(c.getColumnIndex("g_id")));
            ge.gexpense_desc=c.getString(c.getColumnIndex("gexpense_desc"));
            ge.gexpense_amt=Integer.parseInt(c.getString(c.getColumnIndex("gexpense_amt")));
            list.add(ge);
        }
        c.close();
        return list;
    }
    */



    public Debit getDebitInfo(int id){
        Debit db = new Debit();
        String sql="SELECT * FROM `debit` WHERE db_id="+id;
        Cursor c=getReadableDatabase().rawQuery(sql,null);
        while(c.moveToNext()) {
            db.db_id=Integer.parseInt(c.getString(c.getColumnIndex("db_id")));
            db.db_amt=Integer.parseInt(c.getString(c.getColumnIndex("db_amt")));
            db.db_from=c.getString(c.getColumnIndex("db_from"));
            db.db_desc=c.getString(c.getColumnIndex("db_desc"));
        }
        c.close();
        return db;
    }


    public int getDebitSum(){
        String dbTotalSql="SELECT * FROM `debit`";
        Cursor c=getReadableDatabase().rawQuery(dbTotalSql,null);
        int total=0;
        while(c.moveToNext()){
            Debit d=new Debit();
            d.db_amt=Integer.parseInt(c.getString(c.getColumnIndex("db_amt")));
            total=total+d.db_amt;
        }
        c.close();
        return total;

    }
    public int getIncomeSum(){
        String incomeTotalSql="SELECT * FROM `income`";
        Cursor c=getReadableDatabase().rawQuery(incomeTotalSql,null);
        int total=0;
        while(c.moveToNext()){
            Income e=new Income();
            e.income_amt=Integer.parseInt(c.getString(c.getColumnIndex("income_amt")));
            total=total+e.income_amt;
        }
        c.close();
        return total;

    }
    public int getCreditSum(){
        String crTotalSql="SELECT * FROM `credit`";
        Cursor c=getReadableDatabase().rawQuery(crTotalSql,null);
        int total=0;
        while(c.moveToNext()){
            //Log.i("fist line inwhile", "getCreditSum: ");
            Credit e=new Credit();
            //Log.i("above erline", "cramt"+e.cr_amt);
            e.cr_amt=Integer.parseInt(c.getString(c.getColumnIndex("cr_amt")));
            total=total+e.cr_amt;
        }
        c.close();
        return total;

    }
    public int getGexpenseSum(int id){
        String gexpenseTotalSql="SELECT * FROM `gexpense` WHERE `g_id`="+id;
        Log.i("getexpsum", "first line and gid is: "+id+"  "+gexpenseTotalSql);

        Cursor d=getReadableDatabase().rawQuery(gexpenseTotalSql,null);
        int total=0;
        Log.i("before while", "afer total: ");
        while(d.moveToNext()){
            Log.i("whielloop", "firstline: ");
            Gexpense e=new Gexpense();
            e.gexpense_amt=Integer.parseInt(d.getString(d.getColumnIndex("gexpense_amt")));
            Log.i("looop", "expenseamt: "+e.gexpense_amt);
            total=total+e.gexpense_amt;
        }
        d.close();


        //Adding expense from budget amount
        String gbexpenseTotalSql="SELECT * FROM `gbexpense` WHERE `g_id`="+id;
        Cursor e= getReadableDatabase().rawQuery(gbexpenseTotalSql,null);
        while(e.moveToNext()){
            Gbudgetexpense gb= new Gbudgetexpense();
            gb.gbexpense_amt= Integer.parseInt(e.getString(e.getColumnIndex("gbexpense_amt")));
            total=total + gb.gbexpense_amt;
        }
        e.close();


        Log.i("endfunc", "totalis : "+total);
        return total;


    }

    public int getGbudgetSum(int id) {

        String gexpenseTotalSql="SELECT * FROM `gbudget` WHERE `g_id`="+id;

        Cursor d=getReadableDatabase().rawQuery(gexpenseTotalSql,null);
        int total=0;
        while(d.moveToNext()){
             Gbudget gb=new Gbudget();
            Log.i(TAG, "budgetamt inside while: "+gb.budget_amt);
            gb.budget_amt=Integer.parseInt(d.getString(d.getColumnIndex("budget_amt")));
            total=total+gb.budget_amt;
        }
        d.close();
        Log.i(TAG, "total in dbheloper: "+total);
        return total;

    }

    public int getRemBudget(int id){
        int remBudget;
        remBudget=getGbudgetSum(id)-getGbudgetExpense(id);
        return remBudget;
    }

    private int getGbudgetExpense(int id) {
        int gbudgetexpense=0;
        String gbexpenseTotalSql="SELECT * FROM `gbexpense` WHERE `g_id`="+id;
        Cursor e= getReadableDatabase().rawQuery(gbexpenseTotalSql,null);
        while(e.moveToNext()){
            Gbudgetexpense gb= new Gbudgetexpense();
            gb.gbexpense_amt= Integer.parseInt(e.getString(e.getColumnIndex("gbexpense_amt")));
            gbudgetexpense=gbudgetexpense + gb.gbexpense_amt;
        }
        e.close();
        return gbudgetexpense;
    }


    public ArrayList<Income> getIncomeList(){
        ArrayList<Income>list= new ArrayList<Income>();
        String getIncomeListSql="SELECT * FROM `income` ORDER BY income_id DESC";
        Cursor c=getReadableDatabase().rawQuery(getIncomeListSql,null);
        while(c.moveToNext()){

            Income i=new Income();
            i.income_id=Integer.parseInt(c.getString(c.getColumnIndex("income_id")));
            i.income_desc=c.getString(c.getColumnIndex("income_desc"));
            i.income_amt=Integer.parseInt(c.getString(c.getColumnIndex("income_amt")));
            list.add(i);
        }
        c.close();
        return list;
    }
    public ArrayList<Debit> getDebitList(){
        ArrayList<Debit>list= new ArrayList<Debit>();
        String getDebitlistSql="SELECT * FROM `debit` ORDER BY db_id DESC";
        Cursor c=getReadableDatabase().rawQuery(getDebitlistSql,null);
        while(c.moveToNext()){
            Debit i=new Debit();
            i.db_id=Integer.parseInt(c.getString(c.getColumnIndex("db_id")));
            i.db_amt=Integer.parseInt(c.getString(c.getColumnIndex("db_amt")));
            i.db_from=c.getString(c.getColumnIndex("db_from"));
            i.db_desc=c.getString(c.getColumnIndex("db_desc"));
            Log.i("value", "getDebitList: "+i.db_desc);
            i.db_amt=Integer.parseInt(c.getString(c.getColumnIndex("db_amt")));
            list.add(i);
        }
        c.close();
        return list;
    }
    public ArrayList<Group> getGroupList(){
        ArrayList<Group>list= new ArrayList<Group>();
        String getGrouplistsql="SELECT * FROM `grup` ORDER BY g_id DESC";
        Cursor c=getReadableDatabase().rawQuery(getGrouplistsql,null);
        while(c.moveToNext()){
            Group m=new Group();

            m.g_id=Integer.parseInt(c.getString(c.getColumnIndex("g_id")));
            m.g_name=c.getString(c.getColumnIndex("g_name"));
            list.add(m);
        }
        c.close();
        return list;
    }
    public ArrayList<Member> getMemberList(int id){
        ArrayList<Member>list= new ArrayList<Member>();
        String getMemberlistSql="SELECT * FROM `member` where `g_id`="+id;
        Cursor c=getReadableDatabase().rawQuery(getMemberlistSql,null);
        while(c.moveToNext()){
            Member m=new Member();
            m.m_id=Integer.parseInt(c.getString(c.getColumnIndex("m_id")));

            m.g_id=Integer.parseInt(c.getString(c.getColumnIndex("g_id")));
            m.m_name=c.getString(c.getColumnIndex("m_name"));
            list.add(m);
        }
        c.close();
        return list;
    }
    public ArrayList<Gexpense> getGexpenseList(int id){
        ArrayList<Gexpense>list= new ArrayList<Gexpense>();
        String getGexpenselistSql="SELECT * FROM `gexpense` WHERE `g_id`="+id;
        Log.i("gid in query", ""+id);
        Cursor c=getReadableDatabase().rawQuery(getGexpenselistSql,null);
        while(c.moveToNext()){
            Gexpense ge=new Gexpense();
            ge.m_id=Integer.parseInt(c.getString(c.getColumnIndex("m_id")));
            ge.g_id=Integer.parseInt(c.getString(c.getColumnIndex("g_id")));
            ge.gexpense_id=Integer.parseInt(c.getString(c.getColumnIndex("gexpense_id")));
            ge.gexpense_amt=Integer.parseInt(c.getString(c.getColumnIndex("gexpense_amt")));
            ge.gexpense_desc=c.getString(c.getColumnIndex("gexpense_desc"));
            list.add(ge);
        }
        c.close();
        return list;
    }
//Get expense done from group budget
    public ArrayList<Gbudgetexpense> getGbexpenseList(int id){
        ArrayList<Gbudgetexpense>list= new ArrayList<Gbudgetexpense>();
        String getGbexpenselistSql="SELECT * FROM `gbexpense` WHERE `g_id`="+id;
        Log.i("gid in query", ""+id);
        Cursor c=getReadableDatabase().rawQuery(getGbexpenselistSql,null);
        while(c.moveToNext()){
            Gbudgetexpense ge=new Gbudgetexpense();
            ge.g_id=Integer.parseInt(c.getString(c.getColumnIndex("g_id")));
            ge.gbexpense_id=Integer.parseInt(c.getString(c.getColumnIndex("gbexpense_id")));
            ge.gbexpense_amt=Integer.parseInt(c.getString(c.getColumnIndex("gbexpense_amt")));
            ge.gbexpense_desc=c.getString(c.getColumnIndex("gbexpense_desc"));
            list.add(ge);
        }
        c.close();
        return list;
    }

    public Expense getExpenseInfo(int id){
        Expense e = new Expense();
        String sql="SELECT * FROM `expense` WHERE expense_id="+id;
        Cursor c=getReadableDatabase().rawQuery(sql,null);
        while(c.moveToNext()) {
            e.expense_id=Integer.parseInt(c.getString(c.getColumnIndex("expense_id")));
            e.expense_amt=Integer.parseInt(c.getString(c.getColumnIndex("expense_amt")));
            e.expense_detail=c.getString(c.getColumnIndex("expense_detail"));
        }
        c.close();
        return  e;

    }
    public Income getIncomeInfo(int id){
        Income e = new Income();
        String sql="SELECT * FROM `income` WHERE income_id="+id;
        Cursor c=getReadableDatabase().rawQuery(sql,null);
        while(c.moveToNext()) {
            e.income_id=Integer.parseInt(c.getString(c.getColumnIndex("income_id")));
            e.income_amt=Integer.parseInt(c.getString(c.getColumnIndex("income_amt")));
            e.income_desc=c.getString(c.getColumnIndex("income_desc"));
        }
        c.close();
        return e;

    }
    public Credit getCreditInfo(int id){
        Credit cr = new Credit();
        String sql="SELECT * FROM `credit` WHERE cr_id="+id;
        Cursor c=getReadableDatabase().rawQuery(sql,null);
        while(c.moveToNext()) {
            cr.cr_id=Integer.parseInt(c.getString(c.getColumnIndex("cr_id")));
            cr.cr_amt=Integer.parseInt(c.getString(c.getColumnIndex("cr_amt")));
            cr.cr_to=c.getString(c.getColumnIndex("cr_to"));
            cr.cr_desc=c.getString(c.getColumnIndex("cr_desc"));
        }
        c.close();
        return cr;

    }
    public Member getMemberInfo(int id){
        Member m = new Member();
        String sql="SELECT * FROM `member` WHERE m_id="+id;
        Cursor c=getReadableDatabase().rawQuery(sql,null);
        while(c.moveToNext()) {

            m.m_name=c.getString(c.getColumnIndex("m_name"));

        }
        c.close();
        return m;
    }

    public Gexpense getGexpenseinfo(int id){
        Gexpense ge= new Gexpense();
        String sql="SELECT * FROM `Gexpense` WHERE gexpense_id="+id;
        Cursor c=getReadableDatabase().rawQuery(sql,null);
        while(c.moveToNext()){
            ge = new Gexpense();
            ge.m_id=Integer.parseInt(c.getString(c.getColumnIndex("m_id")));
            ge.g_id=Integer.parseInt(c.getString(c.getColumnIndex("g_id")));
            ge.gexpense_id=Integer.parseInt(c.getString(c.getColumnIndex("gexpense_id")));
            ge.gexpense_amt=Integer.parseInt(c.getString(c.getColumnIndex("gexpense_amt")));
            ge.gexpense_desc=c.getString(c.getColumnIndex("gexpense_desc"));
        }
        c.close();
        return ge;

    }

    public ArrayList<Credit> getCreditList(){
        ArrayList<Credit>list= new ArrayList<Credit>();
        String getCreditlistSql="SELECT * FROM `credit` ORDER BY cr_id DESC";
        Cursor c=getReadableDatabase().rawQuery(getCreditlistSql,null);
        while(c.moveToNext()){

            Credit i=new Credit();
            i.cr_id=Integer.parseInt(c.getString(c.getColumnIndex("cr_id")));
            i.cr_amt=Integer.parseInt(c.getString(c.getColumnIndex("cr_amt")));
            i.cr_to=c.getString(c.getColumnIndex("cr_to"));
            i.cr_desc=c.getString(c.getColumnIndex("cr_desc"));

            Log.i("value", "getCreditList: "+i.cr_desc);
            i.cr_amt=Integer.parseInt(c.getString(c.getColumnIndex("cr_amt")));
            list.add(i);
        }
        c.close();
        return list;
    }

    public int getMemberId(String name){
        Log.i("name", "is: "+name);
        Member m = new Member();
        String sql="SELECT * FROM `member` WHERE m_name='"+name+"'";
        Log.i("Sql", "is: "+sql);
        Cursor c=getReadableDatabase().rawQuery(sql,null);
        while(c.moveToNext()) {

            m.m_id=Integer.parseInt(c.getString(c.getColumnIndex("m_id")));

        }
        c.close();
        return m.m_id;
    }

    public int moneyGiven(int mid){
        int expensemoney=0;
        int budgetmoney=0;
        int money;
        Log.i("dbfunction", "memberid: "+mid);
        String expensesql="SELECT * FROM `gexpense` WHERE `m_id`="+mid;
        String budgetsql= "SELECT * FROM `gbudget` WHERE `m_id`="+mid;
        Log.i("sql", "is: "+expensesql);
        Cursor c=getReadableDatabase().rawQuery(expensesql,null);
        //get amount from expense
        while(c.moveToNext()){
            Log.i("firstline", "whileloopo: ");
            expensemoney=expensemoney+Integer.parseInt(c.getString(c.getColumnIndex("gexpense_amt")));
            Log.i("inside while", "money: "+expensemoney);
        }

        //get amount from budget given
        Cursor cd=getReadableDatabase().rawQuery(budgetsql,null);
        while (cd.moveToNext()){
            budgetmoney=budgetmoney+Integer.parseInt(cd.getString(cd.getColumnIndex("budget_amt")));


        }

        money=expensemoney + budgetmoney;
        c.close();
        Log.i("expensemoney",""+expensemoney);
        Log.i("budgetmoney",""+budgetmoney);
        Log.i("retunedmoney",""+money);
        Log.i("b4 return", "moneyval: "+money);
        return money;

    }

    public void updateExpense(int id, ContentValues cv){
        getWritableDatabase().update("expense",cv,"expense_id="+id,null);
    }

    public void updateIncome(int id, ContentValues cv){
        getWritableDatabase().update("income",cv,"income_id="+id,null);
    }

    public void updateCredit(int id, ContentValues cv){
        getWritableDatabase().update("credit",cv,"cr_id="+id,null);
    }

    public void updateDebit(int id, ContentValues cv){
        getWritableDatabase().update("debit",cv,"db_id="+id,null);
    }

    public void updateGexpense(int id, ContentValues cv){
        getWritableDatabase().update("Gexpense",cv,"gexpense_id="+id,null);
    }

    public void updateGbudgetexpense(int id, ContentValues cv){
        getWritableDatabase().update("gbudgetexpense",cv,"gbexpense_id="+id,null);
    }

    public void updateMember(int id, ContentValues cv){
        Log.i("fist line", "updateMember: "+id);
        getWritableDatabase().update("member",cv,"m_id="+id,null);
    }

    public void deleteExpense(int id){

        getWritableDatabase().delete("expense","expense_id="+id,null);
    }

    public void deleteIncome(int id){

        getWritableDatabase().delete("income","income_id="+id,null);
    }

    public void deleteCredit(int id){

        getWritableDatabase().delete("credit","cr_id="+id,null);
    }

    public void deleteDebit(int id){

        getWritableDatabase().delete("debit","db_id="+id,null);
    }

    public void deleteMember(int id){

        getWritableDatabase().delete("member","m_id="+id,null);
    }

    public void deleteGexpense(int id){

        getWritableDatabase().delete("gexpense","gexpense_id="+id,null);
    }


    public void insertUser(ContentValues cv){

        getWritableDatabase().insert("user","",cv);
    }

    public void insertGroup(ContentValues cv){

        getWritableDatabase().insert("grup","",cv);
    }

    public void insertGexpense(ContentValues cv){

        getWritableDatabase().insert("gexpense","",cv);
    }

    public void insertGbudgetExpense(ContentValues cv){
        getWritableDatabase().insert("gbexpense","",cv);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public ArrayList<Gbudget> getGbudetList(int gid) {
        ArrayList<Gbudget> list=new ArrayList<>();
        String sql="SELECT * FROM `Gbudget` WHERE g_id="+gid;
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        while (cursor.moveToNext()){
            Gbudget gbudget=new Gbudget();
            gbudget.budget_id=Integer.parseInt(cursor.getString(cursor.getColumnIndex("budget_id")));
            gbudget.budget_amt=Integer.parseInt(cursor.getString(cursor.getColumnIndex("budget_amt")));
            gbudget.m_id=Integer.parseInt(cursor.getString(cursor.getColumnIndex("m_id")));
            gbudget.g_id=Integer.parseInt(cursor.getString(cursor.getColumnIndex("g_id")));
            list.add(gbudget);
        }
        cursor.close();
        return list;
    }


}
