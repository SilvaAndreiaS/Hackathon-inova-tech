package com.inovatech.salude

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// InovaTech App - Solicitacao de Medicamentos (Jetpack Compose)
@Composable
fun InovaTechApp() {
    var telaAtual by remember { mutableStateOf(1) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF5F7FA)
    ) {
        when (telaAtual) {
            1 -> TelaPrincipal(onNavegar = { telaAtual = 2 })
            2 -> TelaDetalhesMedicamento(onNavegar = { telaAtual = 3 })
            3 -> TelaCarrinhoECheckout(onNavegar = { telaAtual = 4 })
            4 -> TelaConfirmacao(onNavegar = { telaAtual = 5 })
            5 -> TelaArtigosEFeed(onNavegar = { telaAtual = 1 })
        }
    }
}

@Composable
fun TelaPrincipal(onNavegar: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("InovaTech Saúde", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF006A60))
        Text("Solicitação De Medicamento", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onNavegar,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006A60)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ehecha Pohã (Ver Medicamento)")
        }
    }
}

@Composable
fun TelaDetalhesMedicamento(onNavegar: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("OBH Combi", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("$9.99", fontSize = 18.sp, color = Color(0xFF006A60))
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onNavegar,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006A60)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Embojoapy Carrinho-pe (Adicionar ao Carrinho)")
        }
    }
}

@Composable
fun TelaCarrinhoECheckout(onNavegar: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Carrinho & Checkout", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Total: $25.98", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onNavegar,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006A60)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ejogua Ára (Checkout)")
        }
    }
}

@Composable
fun TelaConfirmacao(onNavegar: () -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("✓ Compra Realizada com Sucesso", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF006A60))
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onNavegar,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006A60))
        ) {
            Text("Oĩma (Continuar)")
        }
    }
}

@Composable
fun TelaArtigosEFeed(onNavegar: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Artigos e Notícias de Saúde", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Informações sobre COVID-19 e prevenção", fontSize = 14.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onNavegar,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006A60))
        ) {
            Text("Jevy Oñepyrũme (Voltar ao Início)")
        }
    }
}
