package me.dodas.financeplanner.models;

import java.util.ArrayList;
import java.util.List;

public class MonthlyRegister {

    private String id;

    private List<Registry> revenues = new ArrayList<>();
    private List<Registry> expenses = new ArrayList<>();
    private double totalAmountRev = 0;
    private double totalAmountExp = 0;
    private int revenueQuantityValue = 0;
    private int expenseQuantityValue = 0;

    public int getRevenueQuantityValue() {
        return revenueQuantityValue;
    }

    public int getExpenseQuantityValue() {
        return expenseQuantityValue;
    }

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

    public String getId() {
        return id;
    }

    public MonthlyRegister(String id) {
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
    public List<Registry> getRevenues() {
        return revenues; // Retorna a lista de receitas
    }

    public Registry getRevenueById(String id) {
        if(revenues != null) {
            for(Registry rev : revenues) {
                if (rev.getId() == id) {
                    return rev;
                }
            }
        }

        return null;
    }

    public boolean addRevenue(Registry revenue) {
        totalAmountRev += revenue.getValue();
        revenueQuantityValue++;
        return revenues.add(revenue); // Adiciona uma receita e retorna um booleano indicando sucesso
    }

    public boolean removeRevenue(Registry revenue) {
        totalAmountRev-= revenue.getValue();
        return revenues.remove(revenue); // Remove uma receita e retorna um booleano indicando sucesso
    }

    public void editRevenue(int index, Registry revenues) {
        this.revenues.remove(index);
        this.revenues.add(revenues);
    }

    // Métodos para manipulação de despesas
    public List<Registry> getExpenses() {
        return expenses; // Retorna a lista de despesas
    }

    public Registry getExpensesById(String id) {
        if(expenses != null) {
            for(Registry exp : expenses) {
                if(exp.getId() == id) {
                    return exp;
                }
            }
        }

        return null;
    }

    public boolean addExpense(Registry expense) {
        totalAmountExp+= expense.getValue();
        expenseQuantityValue++;
        return expenses.add(expense); // Adiciona uma despesa e retorna um booleano indicando sucesso
    }

    public boolean removeExpense(Registry expense) {
        totalAmountExp-= expense.getValue();
        return expenses.remove(expense); // Adiciona uma despesa e retorna um booleano indicando sucesso
    }

    public void editExpenses(int index, Registry expenses) {
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