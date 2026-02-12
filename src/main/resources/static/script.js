document.getElementById('travelForm').addEventListener('submit', async function(e) {
    e.preventDefault();

    // 1. FOR INPUTS
    const source = document.getElementById('source').value.trim();
    const destination = document.getElementById('destination').value.trim();
    const duration = document.getElementById('duration').value.trim();
    const budget = document.getElementById('budget').value.trim();
    const interests = document.getElementById('interests').value.trim();

    // 2. Validation
    if (!source || !destination || !duration || !budget) {
        alert("Please fill in all the details first! 📝");
        return;
    }

    // 3. UI RESET
    const loading = document.getElementById('loading');
    const resultBox = document.getElementById('result');
    const submitBtn = document.querySelector('.btn-glow');

    resultBox.style.display = 'none';
    loading.style.display = 'block';
    submitBtn.disabled = true;
    submitBtn.innerText = "Planning your trip... ✈️";

    // 4. DATA PACK
    const formData = { source, destination, duration, budget, interests };

    try {
        // 5. API CALL
        const response = await fetch('/api/travel/create', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData)
        });

        if (!response.ok) throw new Error('Server Error');

        const data = await response.json();

        // 6. RESULT
        document.getElementById('markdown-content').innerHTML = marked.parse(data.aiGeneratedPlan);

        loading.style.display = 'none';
        resultBox.style.display = 'block';

    } catch (error) {
        alert("Something went wrong! Check console.");
        console.error(error);
        loading.style.display = 'none';
    } finally {
        submitBtn.disabled = false;
        submitBtn.innerText = "Generate My Plan 🚀";
    }
});