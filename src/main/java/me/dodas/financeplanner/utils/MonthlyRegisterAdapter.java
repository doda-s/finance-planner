package me.dodas.financeplanner.utils;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import me.dodas.financeplanner.models.Expense;
import me.dodas.financeplanner.models.MonthlyRegister;
import me.dodas.financeplanner.models.Revenue;

public class MonthlyRegisterAdapter implements JsonDeserializer<MonthlyRegister> {

    @Override
    public MonthlyRegister deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jdc)
            throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        // Json array de registros, podendo ser array de expenses ou revenues
        JsonArray registryArray;

        String mrId = jsonObject.get("id").getAsString();
        MonthlyRegister monthlyRegister = new MonthlyRegister(mrId);
        
        // Desserialização de Revenue
        registryArray = jsonObject.getAsJsonArray("revenues");
        if (registryArray != null) {
            for(JsonElement element : registryArray) {
                monthlyRegister.addRevenue(jdc.deserialize(element, Revenue.class));
            }
        }

        // Desserialização de Expense
        registryArray = jsonObject.getAsJsonArray("expenses");
        if (registryArray != null) {
            for(JsonElement element : registryArray) {
                monthlyRegister.addExpense(jdc.deserialize(element, Expense.class));
            }
        }

        return monthlyRegister;
    }
    
}
