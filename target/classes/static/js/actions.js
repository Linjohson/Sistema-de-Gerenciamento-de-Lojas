function confirmDelete(type) {
    return confirm('Confirma exclusão do ' + type + '?');
}

// Aplicar máscaras nos campos de formulário

// Máscara para Telefone: (XX) XXXXX-XXXX
function aplicarMascaraTelefone(input) {
    input.addEventListener('input', function(e) {
        let value = e.target.value.replace(/\D/g, '');
        
        if (value.length <= 11) {
            if (value.length <= 2) {
                value = value.replace(/(\d{1,2})/, '($1');
            } else if (value.length <= 7) {
                value = value.replace(/(\d{2})(\d{1,5})/, '($1) $2');
            } else {
                value = value.replace(/(\d{2})(\d{5})(\d{1,4})/, '($1) $2-$3');
            }
        }
        
        e.target.value = value;
    });
}

// Máscara para CEP: XXXXX-XXX
function aplicarMascaraCEP(input) {
    input.addEventListener('input', function(e) {
        let value = e.target.value.replace(/\D/g, '');
        
        if (value.length <= 8) {
            if (value.length <= 5) {
                value = value.replace(/(\d{1,5})/, '$1');
            } else {
                value = value.replace(/(\d{5})(\d{1,3})/, '$1-$2');
            }
        }
        
        e.target.value = value;
    });
}

// Máscara para CNPJ: XX.XXX.XXX/XXXX-XX
function aplicarMascaraCNPJ(input) {
    input.addEventListener('input', function(e) {
        let value = e.target.value.replace(/\D/g, '');
        
        if (value.length <= 14) {
            if (value.length <= 2) {
                value = value.replace(/(\d{1,2})/, '$1');
            } else if (value.length <= 5) {
                value = value.replace(/(\d{2})(\d{1,3})/, '$1.$2');
            } else if (value.length <= 8) {
                value = value.replace(/(\d{2})(\d{3})(\d{1,3})/, '$1.$2.$3');
            } else if (value.length <= 12) {
                value = value.replace(/(\d{2})(\d{3})(\d{3})(\d{1,4})/, '$1.$2.$3/$4');
            } else {
                value = value.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{1,2})/, '$1.$2.$3/$4-$5');
            }
        }
        
        e.target.value = value;
    });
}

// Máscara para CPF: XXX.XXX.XXX-XX
function aplicarMascaraCPF(input) {
    input.addEventListener('input', function(e) {
        let value = e.target.value.replace(/\D/g, '');
        
        if (value.length <= 11) {
            if (value.length <= 3) {
                value = value.replace(/(\d{1,3})/, '$1');
            } else if (value.length <= 6) {
                value = value.replace(/(\d{3})(\d{1,3})/, '$1.$2');
            } else if (value.length <= 9) {
                value = value.replace(/(\d{3})(\d{3})(\d{1,3})/, '$1.$2.$3');
            } else {
                value = value.replace(/(\d{3})(\d{3})(\d{3})(\d{1,2})/, '$1.$2.$3-$4');
            }
        }
        
        e.target.value = value;
    });
}

// Máscara para Moeda (R$): 1.234,56
function aplicarMascaraValor(input) {
    input.addEventListener('input', function(e) {
        let value = e.target.value.replace(/\D/g, '');
        
        // Remover zeros à esquerda
        value = value.replace(/^0+/, '');
        
        if (value.length === 0) {
            e.target.value = '';
        } else if (value.length === 1) {
            e.target.value = '0,0' + value;
        } else if (value.length === 2) {
            e.target.value = '0,' + value;
        } else {
            // Coloca os últimos 2 dígitos como centavos
            let reais = value.slice(0, -2);
            let centavos = value.slice(-2);
            
            // Adiciona separadores de milhar
            reais = reais.replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.');
            
            e.target.value = reais + ',' + centavos;
        }
    });
}

// Aplicar máscaras ao carregar a página
document.addEventListener('DOMContentLoaded', function() {
    // Telefone
    const camposTelefone = document.querySelectorAll('input[id="telefone"]');
    camposTelefone.forEach(input => aplicarMascaraTelefone(input));
    
    // CEP
    const camposCEP = document.querySelectorAll('input[id="cep"]');
    camposCEP.forEach(input => aplicarMascaraCEP(input));
    
    // CNPJ
    const camposCNPJ = document.querySelectorAll('input[id="cnpj"]');
    camposCNPJ.forEach(input => aplicarMascaraCNPJ(input));
    
    // CPF
    const camposCPF = document.querySelectorAll('input[id="cpf"]');
    camposCPF.forEach(input => aplicarMascaraCPF(input));
    
    // Valor (Moeda)
    const camposValor = document.querySelectorAll('input[id="valor"]');
    camposValor.forEach(input => aplicarMascaraValor(input));
    
    // Converter moeda ao submeter formulário
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.addEventListener('submit', function(e) {
            const valorInput = form.querySelector('input[id="valor"]');
            if (valorInput && valorInput.value) {
                // Converte de "1.234,56" para "1234.56"
                valorInput.value = valorInput.value.replace(/\./g, '').replace(',', '.');
            }
        });
    });
});
