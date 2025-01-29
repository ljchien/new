const express = require('express');
const sql = require('mssql');
const app = express();

// 資料庫連線設定
const config = {
    user: 'your_username',
    password: 'your_password', 
    server: 'your_server',
    database: 'your_database',
    options: {
        encrypt: true, // 如果使用Azure,設為true
        trustServerCertificate: false
    }
};

// 建立API路由
app.get('/api/data', async (req, res) => {
    try {
        // 連接資料庫
        await sql.connect(config);
        
        // 執行查詢
        const result = await sql.query('SELECT * FROM your_table');
        
        res.json(result.recordset);
    } catch (err) {
        console.error('資料庫錯誤:', err);
        res.status(500).send('伺服器錯誤');
    } finally {
        sql.close();
    }
});

// 啟動伺服器
const port = 3000;
app.listen(port, () => {
    console.log(`伺服器執行於 http://localhost:${port}`);
});
