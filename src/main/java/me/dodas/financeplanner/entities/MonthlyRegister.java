package me.dodas.financeplanner.entities;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class MonthlyRegister {

    int id;

    List<Revenue> revenues = new ArrayList<>();
    List<Expense> expenses = new ArrayList<>();
    double totalAmountRev = 0;
    double totalAmountExp = 0;

    // parte abaixo foi melhorada pelo chat gpt, to tentando entender melhor ainda
    // atualização: entendi tudo agora, genial...
    protected enum Months {
        January(1),
        February(2),
        March(3),
        April(4),
        May(5),
        June(6),
        July(7),
        August(8),
        September(9),
        October(10),
        November(11),
        December(12);

        private final int monthNumber;

        // Construtor do enum
        Months(int monthNumber) {
            this.monthNumber = monthNumber;
        }

        // Método para obter o número do mês
        public int getMonthNumber() {
            return monthNumber;
        }

    }

    // me perdi ja, mas vou dar meu melhor ksksksks

    public int getId() {
        return id;
    }

    public MonthlyRegister(int id) {
        this.id = id;
    }

    // Método estático para buscar um mês pelo número
    public Months getMonthByNumber(int number) {
        for (Months month : Months.values()) {
            if (month.getMonthNumber() == number) {
                return month;
            }
        }
        return null; // Retorna null se o número não corresponde a nenhum mês
    }

    // ----------------------------------------------------------------------------------

    // Métodos para manipulação de receitas
    public List<Revenue> getRevenues() {
        return revenues; // Retorna a lista de receitas
    }

    public boolean addRevenue(Revenue revenue) {
        totalAmountRev++;
        return revenues.add(revenue); // Adiciona uma receita e retorna um booleano indicando sucesso
    }

    public boolean removeRevenue(Revenue revenue) {
        totalAmountRev--;
        return revenues.remove(revenue); // Remove uma receita e retorna um booleano indicando sucesso
    }

    public void editRevenue(int index, Revenue revenues) {
        this.revenues.remove(index);
        this.revenues.add(revenues);
    }

    // Métodos para manipulação de despesas
    public List<Expense> getExpenses() {
        return expenses; // Retorna a lista de despesas
    }

    public boolean addExpense(Expense expense) {
        totalAmountExp++;
        return expenses.add(expense); // Adiciona uma despesa e retorna um booleano indicando sucesso
    }

    public boolean removeExpense(Expense expense) {
        totalAmountExp--;
        return expenses.remove(expense); // Adiciona uma despesa e retorna um booleano indicando sucesso
    }

    public void editExpenses(int index, Expense expenses) {
        this.expenses.remove(index);
        this.expenses.add(expenses);
    }

    public double getTotalAmountRevenues() {
        return totalAmountRev;
    }

    public double getTotalAmountExpenses() {
        return totalAmountExp;
    }

}
