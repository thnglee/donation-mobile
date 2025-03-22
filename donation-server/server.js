const express = require('express');
const app = express();
const port = 4000;

app.use(express.json());

// Store donations in memory (for testing)
let donations = [];

// GET all donations
app.get('/api/donations', (req, res) => {
    res.json(donations);
});

// POST new donation
app.post('/api/donations', (req, res) => {
    const donation = {
        id: Date.now().toString(),
        amount: req.body.amount,
        paymentType: req.body.paymentType,
        upvotes: req.body.upvotes || 0
    };
    donations.push(donation);
    res.json(donation);
});

app.listen(port, () => {
    console.log(`Server running at http://localhost:${4000}`);
});