package com.example.ebulgarianrailway.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;

import com.example.ebulgarianrailway.CustomClasses.DBTrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DbHelperNew {
    public static final HashMap<String,String> stations = new HashMap<String,String>(){{

        put("софия","sofia");put("пловдив","plovdiv");put("варна","varna");put("горнаоряховица","gorna-orjahovica");put("плевен","pleven");put("перник","pernik");put("мездра","mezdra");put("бургас","burgas");put("враца","vraca");put("старазагора","stara-zagora");put("асеновград","asenovgrad");put("овчеполци","ovchepolci");put("стамболийски","stambolijski");put("шумен","shumen");put("пазарджик","pazardzhik");put("левски","levski");put("своге","svoge");put("бойчиновци","bojchinovci");put("крумово","krumovo");put("захарнафабрика","zaharna-fabrika");put("метал-спирка","metal-stop");put("димитровград","dimitrovgrad");put("септември","septemvri");put("ямбол","jambol");put("искър","iskar");put("монтана","montana");put("горнабаня","gorna-banja");put("червенбряг","cherven-brjag");put("русе","ruse");put("перникразпределит.","pernik-razpredelit");put("елинпелин","elin-pelin");put("брусарци","brusarci");put("лом","lom");put("подуянепътническа","podujane-passenger");put("търговище","targovishte");put("великотърново","veliko-tarnovo");put("карлово","karlovo");put("костенец","kostenec");put("казанлък","kazanlak");put("карнобат","karnobat");put("павликени","pavlikeni");put("сливен","sliven");put("радомир","radomir");put("първомай","parvomaj");put("добрич","dobrich");put("повеляново","poveljanovo");put("цареваливада","tsareva-livada");put("видин","vidin");put("белово","belovo");put("благоевград","blagoevgrad");put("кюстендил","kjustendil");put("вакарел","vakarel");put("софиясевер","sofia-sever");put("трявна","trjavna");put("белослав","beloslav");put("даскалово","daskalovo");put("белозем","belozem");put("новазагора","nova-zagora");put("криводол","krivodol");put("айтос","ajtos");put("батановци","batanovci");put("провадия","provadija");put("свищов","svishtov");put("роман","roman");put("дряново","drjanovo");put("каспичан","kaspichan");put("церово","cerovo");put("садово","sadovo");put("полскитръмбеш","polski-trambesh");put("дупница","dupnica");put("тулово","tulovo");put("лакатник","lakatnik");put("ихтиман","ihtiman");put("попово","popovo");put("габрово","gabrovo");put("банкя","bankja");put("дългопол","dalgopol");put("земен","zemen");put("двемогили","dve-mogili");put("стражица","strazhica");put("филипово","filipovo");put("ловеч","lovech");put("чирпан","chirpan");put("ракево","rakevo");put("ромча","romcha");put("дъбово","dabovo");put("синдел","sindel");put("бов","bov");put("луково","lukovo");put("враца-спирка","vraca-st");put("самуил","samuil");put("трънак","tranak");put("зверино","zverino");put("скутаре","skutare");put("тодоркаблешков","todor-kableshkov");put("иваново","ivanovo");put("костинброд","kostinbrod");put("ябълково","jabalkovo");put("орлин","orlin");put("курило","kurilo");put("борово","borovo");put("илиянци","ilijanci");put("камено","kameno");put("реброво","rebrovo");put("пещера","peshtera");put("василовци","vasilovci");put("сталийскамахала","stalijska-mahala");put("комунари","komunari");put("тракия","trakija");put("бяла","bjala");put("верила","verila");put("велинград","velingrad");put("хисар","hisar");put("вр.депопловдив-спирка","vr-depo-plovdiv-sp");put("орешец","oreshec");put("владотричков","vlado-trichkov");put("телиш","telish");put("медковец","medkovec");put("поповица","popovica");put("стралджа","straldzha");put("оризово","orizovo");put("новашипка","nova-shipka");put("баня","banja");put("томпсън","tompsan");put("белиизвор","beli-izvor");put("зимница","zimnica");put("русеразпределителна","ruse-razpredelitelna");put("калояновец","kalojanovec");put("струя","struja");put("морава","morava");put("кърджали","kardzhali");put("варна-спирка","varna-sp");put("величково","velichkovo");put("момчилград","momchilgrad");put("вълчидол","valchi-dol");put("бургас-товарна","burgas-tovarna");put("кадиево","kadievo");put("графигнатиево","graf-ignatievo");put("чешнигирово","cheshnegirovo");put("горнидъбник","gorni-dabnik");put("генералтодоров","general-todorov");put("езерово","ezerovo");put("михайлово","mihajlovo");put("петрич","petrich");put("русетоварна-спирка","ruse-tovarna-sp");put("сливница-спирка","slivnica-sp");put("крум","krum");put("тръстиково","trastikovo");put("плачковци","plachkovci");put("добрина","dobrina");put("тополидол","topoli-dol");put("свиленград","svilengrad");put("крушето","krusheto");put("градище","gradishte");put("катуница","katunica");put("смирненски","smirnenski");put("твърдица","tvardica");put("разград","razgrad");put("шишковци","shishkovci");put("ханаспарух","han-asparuh");put("дончево","donchevo");put("калища","kalishta");put("раждавица","razhdavica");put("владимирпавлов","vladimir-pavlov");put("аспарухово","asparuhovo");put("ерден","erden");put("чернагора","cherna-gora");put("скобелево","skobelevo");put("суворово","suvorovo");put("драгичево","dragichevo");put("долнамахала","dolna-mahala");put("александрово","aleksandrovo");put("кумарица","kumarica");put("генералтошево","general-toshevo");put("бутово","butovo");put("петкокаравелово","petko-karavelovo");put("куртовоконаре","kurtovo-konare");put("страшимирово","strashimirovo");put("надежда-спирка","nadezhda");put("прослав","proslav");put("ковачево","kovachevo");put("карагеоргиево","karageorgievo");put("волуяк","volujak");put("берковица","berkovica");put("бяга","bjaga");put("казичене","kazichene");put("драгомирово","dragomirovo");put("кунино","kunino");put("исперих","isperih");put("виница","vinica");put("владая","vladaja");put("отдих","otdih");put("разделна","razdelna");put("дреновец","drenovec");put("харманли","harmanli");put("димово","dimovo");put("пордим","pordim");put("маврудово","mavrudovo");put("симеоновград","simeonovgrad");put("моминаклисура","momina-klisura");put("ветово","vetovo");put("славяново","slavjanovo");put("полскокосово","polsko-kosovo");put("пирдоп","pirdop");put("сандански","sandanski");put("труд","trud");put("хаджиево","hadzhievo");put("нованадежда","nova-nadezhda");put("силистра","silistra");put("караджалово","karadzhalovo");put("драгоман","dragoman");put("плевензапад","pleven-zapad");put("раданово","radanovo");put("шивачево","shivachevo");put("птичар","ptichar");put("черничево","chernichevo");put("царевци","carevci");put("връбница","vrabnica");put("мърчево","marchevo");put("юнак","junak");put("кардам","kardam");put("любимец","ljubimec");put("подем","podem");put("кермен","kermen");put("сеново","senovo");put("калитиново","kalitinovo");put("костинброд-спирка","kostinbrod-sp");put("власатица","vlasatica");put("моминброд","momin-brod");put("бойка","bojka");put("мадара","madara");put("елисейна","elisejna");put("сливница","slivnica");put("боряна","borjana");put("тъжа","tazha");put("опълченец","opalchenec");put("побиткамък","pobit-kamak");put("обеля","obelja");put("кривабара","kriva-bara");put("маноле","manole");put("якоруда","jakoruda");put("долноезерово","dolno-ezerovo");put("дибич","dibich");put("брацигово","bracigovo");put("гълъбник","galabnik");put("желен","zhelen");put("куцина","kucina");put("ловец","lovec");put("летница","letnica");put("михалци-стамболово","mihalci-stambolovo");put("коньово","konjovo");put("боровци","borovci");put("лютиброд","ljutibrod");put("банско","bansko");put("божурище","bozhurishte");put("копиловци","kopilovci");put("оборище","oborishte");put("кочово","kochovo");put("надарево","nadarevo");put("самуилово","samuilovo");put("разлог","razlog");put("чернозем","chernozem");put("комарево","komarevo");put("церковски","cerkovski");put("троян","trojan");put("тополица","topolica");put("николаево","nikolaevo");put("козарско","kozarsko");put("гривица","grivica");put("калояново","kalojanovo");put("чернево","chernevo");put("меричлери-спирка","merichleri-sp");put("моминпроход","momin-prohod");put("горнамахала","gorna-mahala");put("кликач","klikach");put("долнидъбник","dolni-dabnik");put("асеновци","asenovci");put("гулийнабаня","gulijna-banja");put("ханкрум","han-krum");put("лозарево","lozarevo");put("карлуково","karlukovo");put("долнираковец","dolni-rakovec");put("синитово","sinitovo");put("житница","zhitnica");put("подкова","podkova");put("огняново","ognjanovo");put("трайково","trajkovo");put("розино","rozino");put("охрид","ohrid");put("цветино","cvetino");put("соколово","sokolovo");put("звъничево","zvanichevo");put("генералкиселово","general-kiselovo");put("габровница","gabrovnica");put("дунавци","dunavci");put("копаница","kopanica");put("бобовдол","bobov-dol");put("желювойвода","zhelju-vojvoda");put("ловеч-спирка","lovech-sp");put("горскодюлево","gorsko-djulevo");put("венчан","venchan");put("добринище","dobrinishte");put("златица","zlatica");put("калофер","kalofer");put("съботковци","sabotkovci");put("габарево","gabarevo");put("просторно","prostorno");put("манолово","manolovo");put("партизани","partizani");put("долноцеровене","dolno-cerovene");put("рускабяла","ruska-bjala");put("широкополе","shiroko-pole");put("дралфа","dralfa");put("живково","zhivkovo");put("дъбене","dabene");put("симитли","simitli");put("хасково","haskovo");put("лесичери","lesicheri");put("калоянци","kalojanci");put("иваняне","ivanjane");put("белица","belica");put("ребърково","rebarkovo");put("благойзахариев","blagoj-zahariev");put("янтра","jantra");put("мокрище","mokrishte");put("егълница","egalnica");put("дамяница","damjanica");put("перперек","perperek");put("богданци","bogdanci");put("дъскотна","daskotna");put("сборище","sborishte");put("завет","zavet");put("смядово","smjadovo");put("невша","nevsha");put("синьобърдо","sinjo-bardo");put("ресен","resen");put("християново","hristijanovo");put("струмяни","strumjani");put("сталево","stalevo");put("тополите","topolite");put("павелбаня","pavel-banja");put("дойренци","dojrenci");put("дряново-спирка","drjanovo-st");put("атолово","atolovo");put("каменец","kamenec");put("черноград","chernograd");put("мездраюг","mezdra-jug");put("стамболово","stambolovo");put("клисура","klisura");put("търничани","tarnicheni");put("тракия(шумен)","trakija-shumen");put("жабляно","zhabljano");put("сестримо","sestrimo");put("джулюница","dzhuljunica");put("климаш","klimash");put("прибой","priboj");put("каменникариери","kamenni-karieri");put("димитриево","dimitrievo");put("юруково","jurukovo");put("кричим","krichim");put("веринско","verinsko");put("ясен","jasen");put("мурсалево","mursalevo");put("дагоново","dagonovo");put("умаревци","umarevci");put("асеново","asenovo");put("кърнаре","karnare");put("свобода","svoboda");put("осетеново","osetenovo");put("кочево","kochevo");put("иванвазово","ivan-vazovo");put("струпец","strupec");put("ореш","oresh");put("ягодово","jagodovo");put("жеглица","zheglica");put("буново","bunovo");put("хитрино","hitrino");put("василлевски","vasil-levski");put("балкан","balkan");put("черганово","cherganovo");put("кресна","kresna");put("девня","devnja");put("банковица","bankovica");put("ганчовец","ganchovec");put("самоводене","samovodene");put("черквица","cherkvica");put("радкодимитриев","radko-dimitrievo");put("делян","deljan");put("голямодряново","goljamo-drjanovo");put("подгорица","podgorica");put("еленино","elenino");put("иванковци","ivankovci");put("поликраище","polikrajshte");put("борущица","borushtica");put("благоевград-спирка","blagoevgrad-st");put("копривщица","koprivshtica");put("божурица","bozhurica");put("бялбряг","bjal-brjag");put("бисер","biser");put("медовина","medovina");put("глумче","glumche");put("пролет","prolet");put("лазарпампоров","lazar-pamporov");put("козаревец","kozarevec");put("голямосело","goljamo-selo");put("безименна","bezimenna");put("рогозче","rogozche");put("воднянци","vodnjanci");put("церовица","cerovica");put("генералковачев","general-kovachev");put("варвара","varvara");put("долнокамарци","dolno-kamarci");put("кулата","kulata");put("червенавода","chervena-voda");put("ботево","botevo");put("аврамово","avramovo");put("сомовит","somovit");put("дъбовамахала","dabova-mahala");put("храбърско","hrabarsko");put("божковци","bozhkovci");put("солнамина","solna-mina");put("левище","levishte");put("сахране","sahrane");put("маринополе","marino-pole");put("благово","blagovo");put("христоданово","hristo-danovo");put("леска","leska");put("дулово","dulovo");put("чеканчево","chekanchevo");put("столник","stolnik");put("калугерица","kalugerica");put("правда","pravda");put("челопеч","chelopech");put("здравец","zdravec");put("жп.школа-лакатник","zhp-shkola-lakatnik");put("крета","kreta");put("ветрен","vetren");put("песнопой","pesnopoj");put("антон","anton");put("черниче","cherniche");put("звезделина","zvezdelina");put("ведрово","vedrovo");put("ботев","botev");put("горноботево","gorno-botevo");put("желъд","zhelad");put("вълчин","valchin");put("добрич-север","dobrich-sever");put("белщица","belshtica");put("макоцево","makocevo");put("одърне","odarne");put("бургас-разпределителна","burgas-razpredelitelna");put("александърдимитров","aleksandar-dimitrov");put("радунци","radunci");put("драгоман-спирка","dragoman-sp");put("кръстец","krastec");put("межден","mezhden");put("рупите","rupite");put("високаполяна","visoka-poljana");put("кресна-спирка","kresna-sp");put("българово","balgarovo");put("костандово","kostandovo");put("съединение","saedinenie");put("оселна","oselna");put("долнивит","dolni-vit");put("радовене","radovene");put("велинградюг","velingrad-jug");put("моравица","moravica");put("петърч","petarch");put("кошарите","kosharite");put("брусен","brusen");put("арковна","arkovna");put("стърмен","starmen");put("люляково","ljuljakovo");put("кремиковци","kremikovci");put("танювойвода","tanju-vojvoda");put("голямавода","goljama-voda");put("гурково","gurkovo");put("моминаскала","momina-skala");put("листец","listec");put("книжовник","knizhovnik");put("равнец","ravnec");put("дебелец","debelec");put("стайновци","stajnovci");put("оплетня","opletnja");put("дупница-спирка","dupnica-st");put("горнакрета","gorna-kreta");put("усойка","usojka");put("бобошево","boboshevo");put("палилула","palilula");put("безмер","bezmer");put("завой-спирка","zavoj-sp");put("чифлик","chiflik");put("мирково","mirkovo");put("батишница","batishnica");put("радуй","raduj");put("срацимир","sracimir");put("немирово","nemirovo");put("стряма","strjama");put("гавраилово","gavrailovo");put("чернаместа","cherna-mesta");put("дивдядово","divdjadovo");put("вакарел-спирка","vakarel-sp");put("меричлери","merichleri");put("долнамитрополия","dolna-mitropolija");put("кривня","krivnja");put("голямоново","goljamo-novo");put("йоглав","joglav");put("образцовчифлик","obrazcov-chiflik");put("ивански","ivanski");put("светапетка","sveta-petka");put("кочериново","kocherinovo");put("алфатар","alfatar");put("въбел","vabel");put("дебелилаг","debeli-lag");put("подуянеразпр.ср.р.","podujane-razprsrr");put("марикостино","marikostino");put("каменяк","kamenjak");put("руйно","rujno");put("марек","marek");put("светлина-спирка","svetlina-sp");put("градешница","gradeshnica");put("каравельово","karaveljovo");put("макоцево-спирка","makocevo-sp");put("мазарачево","mazarachevo");put("честово-спирка","chestovo-sp");put("завет-спирка","zavet-sp");put("дружба","druzhba");put("спасово","spasovo");put("тодорово","todorovo");put("милковица","milkovica");put("хърсово","harsovo");put("свежен","svezhen");put("кършалево","karshalevo");put("бъзовец","bazovec");put("ботунец","botunec");put("чинтулово","chintulovo");put("анево","anevo");put("лешница","leshnica");put("кракра","krakra");put("трапезица","trapezica");put("долене","dolene");put("стойчевци","stojchevci");put("желязнаврата","zheljazna-vrata");put("горнамалина","gorna-malina");put("сопот","sopot");put("узунджово","uzundzhovo");put("кипра","kipra");put("бозаджи","bozadzhii");put("преславец","preslavec");put("селиминово","seliminovo");put("скакавица","skakavica");put("бърдоква","bardokva");put("калейца","kalejca");put("кесарево","kesarevo");put("мулетарово","muletarovo");put("мътница","matnica");put("кошов","koshov");put("подвис","podvis");put("обединена","obedinena");put("негушево","negushevo");put("светлен","svetlen");put("голямпоровец","goljam-porovec");put("ястребово","jastrebovo");put("цареваполяна","careva-poljana");put("глухар","gluhar");put("китанчево","kitanchevo");put("среднаарда","sredna-arda");put("горица","gorica");put("невестино","nevestino");put("бачокиро","bacho-kiro");put("елит","elit");put("смолево","smolevo");put("малево","malevo");put("константиново","konstantinovo");put("хераково","herakovo");put("равнополе","ravno-pole");put("кутугерци","kutugerci");put("моряк","morjak");put("кубратово","kubratovo");put("калипетрово","kalipetrovo");put("джебел","dzhebel");put("радевци","radevci");put("прилеп","prilep");put("долносело","dolno-selo");put("яна","jana");put("русалядичин","rusalja-dichin");put("бялополе","belo-pole");put("бенковски","benkovski");put("мост","most");put("бригадир","brigadir");put("чумерна","chumerna");put("маслиново","maslinovo");put("черник","chernik");put("реселец-спирка","reselec-sp");put("разменна","razmenna");put("иганово","iganovo");put("яхиново","jahinovo");put("алдомировци","aldomirovci");put("караджата","karadzhata");put("долапите","dolapite");put("джерман","dzherman");put("ослен","oslen");put("скомля/кантон-43","skomlja-kanton-43");put("полскисеновец","polski-senovec");put("бокиловци","bokilovci");put("конаре","konare");put("малосело","malo-selo");put("божичен","bozhichen");put("чуковец","chukovec");put("абланица","ablanica");put("соволяно","sovoljano");put("дождевица","dozhdevica");put("дяково","djakovo");put("добродан","dobrodan");put("кондофрей","kondofrej");put("димитовградюг-спирка","dimitrovgrad-jug-sp");put("орешак","oreshak");put("мламолово","mlamolovo");put("казачево","kazachevo");put("езерово-спирка","ezerovo-spirka");put("мирково-спирка","mirkovo-st");put("видбол","vidbol");put("световрачане","svetovrachane");put("ломец","lomec");put("цепина","cepina");put("байково","bajkovo");put("чавдарци","chavdarci");put("черногорово","chernogorovo");put("пейояворов","peio-javorov");put("червена","chervena");put("климат.училище","klimat-uchilishe");put("ясеновец","jasenovec");put("мъдрец","madrec");put("раковски","rakovski");put("велино","velino");put("димитровградсевер","dimitrovgrad-sever");put("стамокостов","stamo-kostov");put("равна","ravna");put("бригадирскаслава","brigadirska-slava");put("негован","negovan");put("маркониколов","marko-nikolov");put("дългалъка","dalga-laka");put("златуша","zlatusha");put("ягода","jagoda");put("строител","stroitel");put("генералколево","general-kolevo");put("железница","zheleznica");put("плиска","pliska");put("острец","ostrec");put("ловеч-юг","lovech-jug");put("добриново","dobrinovo");put("карбинци","karbinci");put("яворовец","javorovec");put("саранци","saranci");put("змейово","zmejovo");put("дропла-спирка","dropla-spirka");put("бръчма","brachma");put("синаговци-гурково","sinagovci-gurkovo");put("макреш","makresh");put("локорско","lokorsko");put("горнаоряховица-пр.парк","gorjahovica-prpark");put("черепиш","cherepish");put("рупци-север","rupci-sever");put("златнанива","zlatna-niva");put("преколница","prekolnica");put("бряст","brjast");put("хумата","humata");put("костапетров","kosta-petrov");put("табачка","tabachka");put("горнаоряховица-разпр.п.","gorna-orjahovica-razprp");put("драсов","drasov");put("сланабара","slana-bara");put("разменна-спирка","razmenna-sp");put("старакресна","stara-kresna");put("гюешево","gjueshevo");put("царацово","caracovo");put("калотина-запад","kalotina-zapad");put("хлевене","hlevene");put("крепост","krepost");put("моруница","morunica");put("ловеч-север","lovech-sever");put("мусачево","musachevo");put("панагюрище","panagjurishte");put("стрелча","strelcha");put("царасен","car-asen");put("смилец","smilec");put("дюлево","djulevo");put("бургас-разпределителна-спирка","burgas-razpredelitelna-spirka");put("лозово-спирка","lozovo");put("плоскамогила","ploska-mogila");put("светигеорги","sveti-georgi");put("кариера-скакавица","kariera-skakavica");put("белавода","bela-voda");put("искърскошосе","iskarsko-shose");put("харманлицентър","harmanli-central");put("димитровграджс","dimitrovgrad-rs");put("капъкуле","kapikule");put("стоянмитов","stoyan-mitov");put("якорудскаминералнабаня","yakorudska-mineralna-banya");put("спиркагорнабаня-метростанция","stop-gorna-banja-metro");
    }};

    public interface OnResult{
        void DoWork();
    }

    public static ArrayList<String> corrects = new ArrayList<>();
    public static void Checker(String from,String to,OnResult pass,OnResult fail)
    {
        if (from.equals("")||to.equals(""))
        {
            fail.DoWork();
        }
        else {
            corrects.clear();
            boolean isFromCorrect = false;
            boolean isToCorrect = false;
            for (Map.Entry<String, String> e : stations.entrySet()) {
                if (e.getKey().equals(from) || e.getKey().startsWith(MakeReadable(from))) {
                    isFromCorrect = true;
                    corrects.add(e.getValue());
                    break;
                }
            }
            for (Map.Entry<String, String> e : stations.entrySet()) {
                if (e.getKey().equals(MakeReadable(to)) || e.getKey().startsWith(MakeReadable(to))) {
                    isToCorrect = true;
                    corrects.add(e.getValue());
                    break;
                }
            }
            if (isFromCorrect && isToCorrect) {
                pass.DoWork();
            } else {
                fail.DoWork();
            }
        }
    }

    public static String MakeReadable(TextView s)
    {
       return s.getText().toString().toLowerCase().trim().replaceAll("[0-9 -]","");
    }
    public static String MakeReadable(String s)
    {
        return s.toLowerCase().trim().replaceAll("[0-9 -]","");
    }

    //////////////////////////////////////////////////////////////////////////////


    public static final String ROW_ID = "ID";
    public static final String NAME = "Name";
    public static final String FROM = "From_station";
    public static final String TO = "To_station";

    private static final String TAG = "DBHELPER";
    private static final String DATABASE_NAME = "trains";
    private static final String DATABASE_TABLE = "Favourites";
    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_CREATE =  "CREATE TABLE if not exists "+DATABASE_TABLE+"( " +
                        "ID integer PRIMARY KEY AUTOINCREMENT, " +
                        "From_station text not null, " +
                        "To_station text not null, " +
                        "Name text not null " +
                        ")";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private final Context context;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            onCreate(db);
        }
    }


    public DbHelperNew(Context context) {
        this.context = context;
    }


    public void open() throws SQLException {
        this.mDbHelper = new DatabaseHelper(this.context);
        this.mDb = this.mDbHelper.getWritableDatabase();
    }

    public void close() {
        this.mDbHelper.close();
    }


    public int Insert(String name,String from, String to) {
        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(FROM, from);
        cv.put(TO, to);
        return (int) this.mDb.insert(DATABASE_TABLE, null, cv);
    }


    public boolean Delete(int rowId) {

        return this.mDb.delete(DATABASE_TABLE, ROW_ID + "=" + rowId, null) > 0;
    }


    public ArrayList<DBTrain> getAll() {
        ArrayList<DBTrain> list = new ArrayList<>();
        Cursor cursor = this.mDb.query(DATABASE_TABLE, new String[] { ROW_ID,
                NAME,FROM,TO}, null, null, null, null, null);
        while (cursor.moveToNext()){
           list.add(DBTrain.GetTrainFromCursor(cursor));
        }
        return list;
    }


    public Cursor getTrain(long rowId) throws SQLException {

        Cursor mCursor =

                this.mDb.query(true, DATABASE_TABLE, new String[] { ROW_ID, NAME}, ROW_ID + "=" + rowId, null, //$NON-NLS-1$
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }


    public boolean Update(long rowId, String name,String from, String to) {
        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(FROM, from);
        cv.put(TO, to);
        return this.mDb.update(DATABASE_TABLE, cv, ROW_ID + "=" + rowId, null) > 0; //$NON-NLS-1$
    }
}
