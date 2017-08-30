package np.com.sachinmaharzan.expensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

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
            "\t`budget_amt`\tINTEGER,\n" +
            "\tFOREIGN KEY(`m_id`) REFERENCES member\n" +
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
        Log.i("endfunc", "totalis : "+total);
        return total;


    }

    public int getGbudgetSum(int id) {

        String gexpenseTotalSql="SELECT * FROM `gbudget` WHERE `g_id`="+id;

        Cursor d=getReadableDatabase().rawQuery(gexpenseTotalSql,null);
        int total=0;
        while(d.moveToNext()){
             Gbudget gb=new Gbudget();
            gb.budget_amt=Integer.parseInt(d.getString(d.getColumnIndex("budget_amt")));
            total=total+gb.budget_amt;
        }
        d.close();
        return total;

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

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
