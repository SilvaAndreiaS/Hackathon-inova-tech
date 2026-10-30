package com.inovatech.salude

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InovaTechAppCompleto() {
    // Estado de Navegação Entre as Telas
    var telaAtual by remember { mutableStateOf(0) } // 0: Cadastro, 1: Vitrine, 2: Detalhes, 3: Carrinho, 4: Confirmação, 5: Feed

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF5F7FA)
    ) {
        when (telaAtual) {
            0 -> TelaCadastro(onCadastroConcluido = { telaAtual = 1 })
            1 -> TelaVitrineInicial(
                onNavegarDetalhes = { telaAtual = 2 },
                onNavegarPerfil = { telaAtual = 0 },
                onNavegarFeed = { telaAtual = 5 }
            )
            2 -> TelaDetalhesMedicamento(
                onVoltar = { telaAtual = 1 },
                onComprar = { telaAtual = 3 }
            )
            3 -> TelaCarrinhoCheckout(
                onVoltar = { telaAtual = 2 },
                onFinalizar = { telaAtual = 4 }
            )
            4 -> TelaConfirmacaoPedido(
                onContinuar = { telaAtual = 5 }
            )
            5 -> TelaFeedNoticias(
                onVoltarInicio = { telaAtual = 1 }
            )
        }
    }
}

// TELA 0 — CADASTRO E PREFERÊNCIAS DE ACESSIBILIDADE
@Composable
fun TelaCadastro(onCadastroConcluido: () -> Unit) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var necessidadeAcessibilidade by remember { mutableStateOf("Nenhuma") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("InovaTech Saúde", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color(0xFF006A60))
        Text("Cadastro do Paciente & Perfil", fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome Completo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail ou Cartão SUS") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = telefone,
            onValueChange = { telefone = it },
            label = { Text("Telefone / WhatsApp") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Preferência de Acessibilidade:", fontSize = 14.sp, fontWeight = FontWeight.Medium)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            FilterChip(
                selected = necessidadeAcessibilidade == "Voz",
                onClick = { necessidadeAcessibilidade = "Voz" },
                label = { Text("Comando de Voz") }
            )
            FilterChip(
                selected = necessidadeAcessibilidade == "AltoContraste",
                onClick = { necessidadeAcessibilidade = "AltoContraste" },
                label = { Text("Alto Contraste") }
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = onCadastroConcluido,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006A60))
        ) {
            Text("CADASTRAR E IR PARA A TELA INICIAL", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// TELA 1 — VITRINE INICIAL E INÍCIO DE SOLICITAÇÃO
@Composable
fun TelaVitrineInicial(
    onNavegarDetalhes: () -> Unit,
    onNavegarPerfil: () -> Unit,
    onNavegarFeed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("InovaTech", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF006A60))
            IconButton(onClick = onNavegarPerfil) {
                Icon(Icons.Default.Person, contentDescription = "Perfil")
            }
        }

        Text("Solicitação De Medicamento", fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Procurar remédio ou receita...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                Row {
                    Icon(Icons.Default.Mic, contentDescription = "Busca por Voz")
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.QrCodeScanner, contentDescription = "Scanner")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2F1))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Encomende rapidamente com Prescrição", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onNavegarDetalhes,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006A60))
                ) {
                    Text("Comprar Prescrição")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Produtos Populares", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavegarDetalhes() },
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("OBH Combi", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("Xarope 100ml", color = Color.Gray, fontSize = 12.sp)
                    Text("$9.99", color = Color(0xFF006A60), fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = onNavegarDetalhes,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006A60))
                ) {
                    Text("Ver")
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onNavegarFeed,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004D40))
        ) {
            Text("Ver Artigos de Saúde & Prevenção")
        }
    }
}

// TELA 2 — DETALHES DO MEDICAMENTO
@Composable
fun TelaDetalhesMedicamento(onVoltar: () -> Unit, onComprar: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(onClick = onVoltar) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text("OBH Combi", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Alívio rápido para febre e dor", color = Color.Gray)

        Spacer(modifier = Modifier.height(20.dp))

        Text("Preço:", fontSize = 14.sp)
        Text("$9.99", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF006A60))

        Spacer(modifier = Modifier.height(16.dp))

        Text("Descrição:", fontWeight = FontWeight.Bold)
        Text(
            "Medicamento indicado para o tratamento sintomático de gripe, febre e dores corporais. " +
            "Para uso seguro, siga as orientações médicas e verifique a dosagem prescrita.",
            color = Color.DarkGray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onComprar,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006A60))
        ) {
            Text("ADICIONAR AO CARRINHO & FINALIZAR", fontWeight = FontWeight.Bold)
        }
    }
}

// TELA 3 — CARRINHO E CHECKOUT
@Composable
fun TelaCarrinhoCheckout(onVoltar: () -> Unit, onFinalizar: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(onClick = onVoltar) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
        }

        Text("Resumo do Pedido", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("OBH Combi x1 — $9.99")
                Text("Panadol x2 — $15.99")
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                Text("Total: $25.98", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF006A60))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Forma de Pagamento / Entrega:", fontWeight = FontWeight.Bold)
        Text("• Cartão Visa / Pix")
        Text("• Retirada na Farmácia Popular ou Entrega em Domicílio")

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onFinalizar,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006A60))
        ) {
            Text("CONFIRMAR E FINALIZAR COMPRA", fontWeight = FontWeight.Bold)
        }
    }
}

// TELA 4 — CONFIRMAÇÃO DE COMPRA
@Composable
fun TelaConfirmacaoPedido(onContinuar: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.CheckCircle,
            contentDescription = "Sucesso",
            tint = Color(0xFF006A60),
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Compra Realizada com Sucesso!", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Seu pedido foi registrado no sistema InovaTech. " +
            "Acompanhe as atualizações de envio ou a retirada na sua unidade de saúde.",
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onContinuar,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006A60)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("VER ARTIGOS DE SAÚDE")
        }
    }
}

// TELA 5 — FEED DE NOTÍCIAS E ARTIGOS
@Composable
fun TelaFeedNoticias(onVoltarInicio: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Artigos & Saúde Preventiva", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF006A60))

        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("Prevenção e Cuidados na Febre", fontWeight = FontWeight.Bold)
                Text("Saiba como agir diante dos primeiros sintomas e quando buscar ajuda.", fontSize = 12.sp, color = Color.Gray)
            }
        }

        Card(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("Importância do Acompanhamento IoT", fontWeight = FontWeight.Bold)
                Text("Como a medição contínua de SpO2 e batimentos ajuda seu médico.", fontSize = 12.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onVoltarInicio,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006A60))
        ) {
            Text("VOLTAR À TELA INICIAL")
        }
    }
}
