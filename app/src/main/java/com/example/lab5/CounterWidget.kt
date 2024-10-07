package com.example.lab5

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class CounterWidget : AppWidgetProvider() {

    companion object {
        private var counter = 0  // Переменная для хранения текущего счёта
        const val ACTION_INCREMENT = "ACTION_INCREMENT"
        const val ACTION_RESET = "ACTION_RESET"

        // Метод для обновления виджета
        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val views = RemoteViews(context.packageName, R.layout.counter_widget)

            // Установка текущего счёта в текстовое поле
            views.setTextViewText(R.id.tvCounter, counter.toString())

            // Intent для кнопки "Добавить"
            val incrementIntent = Intent(context, CounterWidget::class.java).apply {
                action = ACTION_INCREMENT
            }
            val incrementPendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                incrementIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.btnIncrement, incrementPendingIntent)

            // Intent для кнопки "Сбросить"
            val resetIntent = Intent(context, CounterWidget::class.java).apply {
                action = ACTION_RESET
            }
            val resetPendingIntent = PendingIntent.getBroadcast(
                context,
                1,
                resetIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.btnReset, resetPendingIntent)

            // Обновление виджета
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (intent.action == ACTION_INCREMENT) {
            counter++
        } else if (intent.action == ACTION_RESET) {
            counter = 0
        }

        val appWidgetManager = AppWidgetManager.getInstance(context)
        val thisWidget = ComponentName(context, CounterWidget::class.java)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget)
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }
}
