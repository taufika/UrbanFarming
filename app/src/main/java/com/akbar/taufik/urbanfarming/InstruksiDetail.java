package com.akbar.taufik.urbanfarming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

public class InstruksiDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruksi_detail);

        String namaTanaman = getIntent().getStringExtra("namaTanaman");
        namaTanaman = namaTanaman.toLowerCase();

        TextView judulInstruksi = (TextView) findViewById(R.id.judulInstruksi);
        judulInstruksi.setText("How to plant " + namaTanaman);

        TextView namaGede = (TextView) findViewById(R.id.namaGede);
        namaGede.setText(namaTanaman.toUpperCase());

        TextView phDLL = (TextView) findViewById(R.id.kondisiPHDLL);
        TextView growingCondition = (TextView) findViewById(R.id.growingCondition);
        TextView harvesting = (TextView) findViewById(R.id.harvesting);

        int gambar;
        switch(namaTanaman.toUpperCase()){
            case "LETTUCE" :
                gambar = R.drawable.lettuce_gede;
                phDLL.setText(Html.fromHtml("<b>pH:</b>  6.0–7.0<br/>\n" +
                        "<b>Plant spacing:</b>  18–30 cm (20–25 heads/m2)<br/>\n" +
                        "<b>Germination time and temperature:</b>  3–7 days; 13–21 °C<br/>\n" +
                        "<b>Growth time:</b>  24–32 days (longer for some varieties)<br/>\n" +
                        "<b>Temperature:</b>  15–22 °C (flowering over 24 °C)<br/>\n" +
                        "<b>Light exposure:</b>  full sun (light shading in warm temperatures)<br/>\n" +
                        "<b>Plant height and width:</b>  20–30 cm; 25–35 cm<br/>\n"));
                growingCondition.setText(Html.fromHtml("<b>Growing conditions:</b> Lettuce is a winter crop. For head growth, the night air temperature should be 3–12 °C, with a day temperature of 17–28 °C. The generative growth is affected by photoperiod and temperature  – extended daylight and warm conditions (>  18  °C) at night cause bolting. Water temperature >  26  °C may also favour bolting and leaf bitterness. The plant has low nutrient demand; however, higher calcium concentrations in water help to prevent tip burn in leaf in summer crops. The ideal pH is 5.8–6.2, but lettuce still grows well with a pH as high as 7, although some iron deficiencies might appear owing to reduced bio-availability of this nutrient above neutrality."));
                harvesting.setText(Html.fromHtml("<b>Harvesting:</b> Harvesting can begin as soon as heads or leaves are large enough to eat. If selling to markets, remove the full plants and roots when harvesting as soon as they reach market weight (250–400 g). Cut the roots out and place them in a compost bin. Harvest early in the morning when leaves are crisp and full of moisture and chill quickly."));
                break;
            case "CABBAGE" :
                gambar = R.drawable.cabbage_gede;
                phDLL.setText(Html.fromHtml("<b>pH:</b>  6–7.2<br/>\n" +
                        "<b>Plant spacing:</b>  60–80 cm (4–8 plants/m2)<br/>\n" +
                        "<b>Germination time and temperature:</b>  4–7 days; 8–29 °C<br/>\n" +
                        "<b>Growth time:</b>  45–70 days from transplanting (depending on varieties and season)<br/>\n" +
                        "<b>Ideal temperature:</b>  15–20 °C (growth stops at > 25 °C)<br/>\n" +
                        "<b>Light exposure:</b>  full sun<br/>\n" +
                        "<b>Plant height and width:</b>  30–60 cm; 30–60 cm<br/>\n"));
                growingCondition.setText(Html.fromHtml("<b>Growing conditions:</b> Cabbage is a winter crop with ideal growing temperatures of 15–20  °C; Cabbage grows best when the heads mature in cooler temperatures, so plan to harvest before daytime temperatures reach 23–25 °C. High concentrations of phosphorus and potassium are essential when the heads begin to grow. Integration with organic fertilizers delivered either on leaves or substrates may be necessary in order to supply plants with adequate levels of nutrients."));
                harvesting.setText(Html.fromHtml("<b>Harvesting:</b> Start harvesting when cabbage heads are firm with a diameter of about 10–15  cm (depending on variety grown). Cut the head from the stem with a sharp knife, and place the outer leaves into the compost bin. If cabbage heads tend to break, it indicates they are over-ripe and should have been harvested earlier."));
                break;
            case "BASELLA" :
                gambar = R.drawable.basil_gede;
                phDLL.setText(Html.fromHtml("<b>pH:</b>  5.5–6.5<br/>\n" +
                        "<b>Plant spacing:</b>  15–25 cm (8–40 plants/m2)<br/>\n" +
                        "<b>Germination time and temperature:</b>  6–7 days with temperatures at 20–25 °C<br/>\n" +
                        "<b>Growth time:</b>  5–6 weeks (start harvesting when plant is 15 cm)<br/>\n" +
                        "<b>Temperature:</b>  18–30 °C, optimal 20–25 °C<br/>\n" +
                        "<b>Light exposure:</b> Sunny or slightly sheltered<br/>\n" +
                        "<b>Plant height and width:</b>  30–70 cm; 30 cm<br/>\n"));
                growingCondition.setText(Html.fromHtml("<b>Growing conditions:</b> Basil seeds need a reasonably high and stable temperature to initiate germination (20–25  °C). Once transplanted in the units, basil grows best in warm to very warm conditions and full exposure to sun. However, better quality leaves are obtained through slight shading. With daily temperatures higher than 27 °C plants should be ventilated or covered with shading nets (20  percent) during strong solar radiation seasons to prevent tip burn."));
                harvesting.setText(Html.fromHtml("<b>Harvesting:</b> The harvest of leaves starts when plants reach 15  cm in height and continues for 30–50  days. Care should be used when handling leaves at harvest to avoid leaf bruising and blackening. It is advisable to remove flowering tips during plant growth to avoid bitter tastes in leaves and encourage branching. However, basil flowers are attractive to pollinators and beneficial insects, so leaving a few flowering plants can improve the overall garden and ensure a constant supply of basil seeds. Basil seeds are a specialty product in some locations."));
                break;
            default:
                gambar = R.drawable.cabbage_gede;
                phDLL.setText(Html.fromHtml("<b>pH:</b>  5.5–6.5<br/>\n" +
                        "        <b>Plant spacing:</b>  15–25 cm (8–40 plants/m2)<br/>\n" +
                        "        <b>Germination time and temperature:</b>  6–7 days with temperatures at 20–25 °C<br/>\n" +
                        "        <b>Growth time:</b>  5–6 weeks (start harvesting when plant is 15 cm)<br/>\n" +
                        "        <b>Temperature:</b>  18–30 °C, optimal 20–25 °C<br/>\n" +
                        "        <b>Light exposure:</b> Sunny or slightly sheltered<br/>\n" +
                        "        <b>Plant height and width:</b>  30–70 cm; 30 cm<br/>"));
                growingCondition.setText(Html.fromHtml("<b>Growing conditions:</b> Basil seeds need a reasonably high and stable temperature to initiate germination (20–25  °C). Once transplanted in the units, basil grows best in warm to very warm conditions and full exposure to sun. However, better quality leaves are obtained through slight shading. With daily temperatures higher than 27 °C plants should be ventilated or covered with shading nets (20  percent) during strong solar radiation seasons to prevent tip burn."));
                harvesting.setText(Html.fromHtml("<b>Harvesting:</b> The harvest of leaves starts when plants reach 15  cm in height and continues for 30–50  days. Care should be used when handling leaves at harvest to avoid leaf bruising and blackening. It is advisable to remove flowering tips during plant growth to avoid bitter tastes in leaves and encourage branching. However, basil flowers are attractive to pollinators and beneficial insects, so leaving a few flowering plants can improve the overall garden and ensure a constant supply of basil seeds. Basil seeds are a specialty product in some locations."));

        }

        ImageView gambarGede = (ImageView) findViewById(R.id.gambarGede);
        gambarGede.setImageResource(gambar);

    }
}
