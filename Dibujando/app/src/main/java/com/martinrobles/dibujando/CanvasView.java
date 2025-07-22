package com.martinrobles.dibujando;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CanvasView extends View {
    private Paint currentPaint; // Pincel actual
    private Bitmap currentBitmap; // Mapa de bits actual
    private Bitmap brushBitmap; // Mapa de bits del pincel
    private List<Quadruple<Path, Paint, Bitmap, List<Pair<Float, Float>>>> paths = new ArrayList<>(); // Lista de caminos
    private List<Pair<Float, Float>> brushBitmapPositions = new ArrayList<>(); // Lista de posiciones del pincel
    private List<Pair<Float,Float>> currentBrushBitmapPositions = new ArrayList<>(); // Lista de posiciones del pincel actual

    // Constructor de la clase con atributos
    public CanvasView(Context context) {
        super(context);
        init(null, 0);
    }

    // Constructor de la clase con atributos y estilo
    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    // Método para inicializar el lienzo
    private void init(AttributeSet attrs, int defStyle) {
        currentPaint = createNewPaint(Color.BLACK); // Establece el color del pincel en negro
    }

    // Método para crear un nuevo Paint
    private Paint createNewPaint(int color) {
        Paint paint = new Paint();
        paint.setStrokeWidth(25f);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }

    // Método para dibujar en el lienzo
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Quadruple<Path, Paint, Bitmap, List<Pair<Float, Float>>> quadruple : paths) {
            if (quadruple.getThird() != null) {
                for (Pair<Float, Float> position : quadruple.getFourth()) {
                    canvas.drawBitmap(quadruple.getThird(), position.first - quadruple.getThird().getWidth() / 2, position.second - quadruple.getThird().getHeight() / 2, null);
                }
            } else {
                for (Pair<Float, Float> position : quadruple.getFourth()) {
                    canvas.drawCircle(position.first, position.second, currentPaint.getStrokeWidth() / 2, quadruple.getSecond());
                }
            }
        }
    }
    /*@Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Quadruple<Path, Paint, Bitmap, List<Pair<Float, Float>>> quadruple : paths) {
            if (quadruple.getThird() != null) {
                for (Pair<Float, Float> position : quadruple.getFourth()) {
                    canvas.drawBitmap(quadruple.getThird(), position.first - quadruple.getThird().getWidth() / 2, position.second - quadruple.getThird().getHeight() / 2, null);
                }
            } else {
                canvas.drawPath(quadruple.getFirst(), quadruple.getSecond());
            }
        }
    }*/

    // Método para detectar el evento de tocar la pantalla
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (currentBitmap == null) {
                    paths.add(new Quadruple<>(new Path(), new Paint(currentPaint), null, new ArrayList<>()));
                } else {
                    Bitmap bitmapCopy = currentBitmap.copy(currentBitmap.getConfig(), true);
                    paths.add(new Quadruple<>(new Path(), new Paint(currentPaint), bitmapCopy, new ArrayList<>()));
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (currentBitmap != null) {
                    Quadruple<Path, Paint, Bitmap, List<Pair<Float, Float>>> quadruple = paths.get(paths.size() - 1);
                    quadruple.getFourth().add(new Pair<>(eventX, eventY));
                } else {
                    Quadruple<Path, Paint, Bitmap, List<Pair<Float, Float>>> quadruple = paths.get(paths.size() - 1);
                    quadruple.getFourth().add(new Pair<>(eventX, eventY));
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }
    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        // Si se toca la pantalla
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // Si se toca la pantalla
                Path path = new Path();
                path.moveTo(eventX, eventY);
                Bitmap bitmapCopy = currentBitmap != null ? currentBitmap.copy(currentBitmap.getConfig(), true) : null;
                paths.add(new Quadruple<>(path, new Paint(currentPaint), bitmapCopy, new ArrayList<>()));
                return true;
            case MotionEvent.ACTION_MOVE: // Si se mueve el dedo por la pantalla
                if (currentBitmap != null) {
                    Quadruple<Path, Paint, Bitmap, List<Pair<Float, Float>>> quadruple = paths.get(paths.size() - 1);
                    quadruple.getFourth().add(new Pair<>(eventX, eventY));
                } else {
                    Quadruple<Path, Paint, Bitmap, List<Pair<Float, Float>>> quadruple = paths.get(paths.size() - 1);
                    quadruple.getFirst().lineTo(eventX, eventY);
                }
                break;
            case MotionEvent.ACTION_UP: // Si se levanta el dedo de la pantalla
                break;
            default:
                return false;
        }

        invalidate(); // Invalida la vista para que se redibuje
        return true;
    }*/

    // Método para establecer el mapa de bits del pincel
    public void setBrushBitmap(Bitmap bitmap) {
        this.brushBitmap = bitmap;
        this.currentBitmap = bitmap; // Asigna el Bitmap a currentBitmap
        this.currentPaint = createNewPaint(Color.TRANSPARENT); // Establece el color del Paint a transparente cuando se selecciona una imagen
    }

    // Método para establecer el color del pincel
    public void setPaintColor(int color) {
        this.currentBitmap = null; // Establece el Bitmap actual en null cuando se selecciona un color
        this.currentPaint = createNewPaint(color);
    }

    // Método para borrar el lienzo
    public void clearCanvas() {
        paths.clear();
        brushBitmapPositions.clear();
        invalidate();
    }
}
