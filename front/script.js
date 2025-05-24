document.getElementById('imc-form').addEventListener('submit', async function(event) {
  event.preventDefault();

  const nome = document.getElementById('nome').value;
  const peso = parseFloat(document.getElementById('peso').value);
  const altura = parseFloat(document.getElementById('altura').value);

  try {
    const response = await fetch('http://localhost:8080/imc', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ nome, peso, altura })
    });

    if (!response.ok) {
      throw new Error(`Erro na requisição: ${response.status}`);
    }

    const data = await response.json();
    document.getElementById('valor-imc').textContent = data.imc.toFixed(2);
    document.getElementById('classificacao').textContent = data.classificacao;
    document.getElementById('resultado').classList.remove('hidden');

  } catch (err) {
    alert('Não foi possível calcular o IMC: ' + err.message);
  }
});
